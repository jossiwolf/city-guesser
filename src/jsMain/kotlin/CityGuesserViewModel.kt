import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import common.lce.Lce
import data.CitiesRepository
import domain.cities
import io.data2viz.geojson.Feature
import io.data2viz.geojson.FeatureCollection
import io.data2viz.geojson.Point
import io.data2viz.geojson.js.asGeoJsonObject

external interface CityGuesserFeatureProperties {

    /**
     * The country the feature is located in.
     */
    @JsName("ADM0NAME")
    val country: String

    /**
     * Whether the feature is considered a megacity
     */
    @JsName("MEGACITY")
    val isMegacity: Int

    /**
     * The scale at which the feature shows up on a map when zooming in
     * https://www.naturalearthdata.com/forums/topic/definition-of-scale-rank-appropriate-attribute-for-size-based-styling/
     */
    @JsName("SCALERANK")
    val minZoom: Int

    /**
     * The feature's name, non-ascii
     */
    @JsName("NAME")
    val name: String

    /**
     * The total population of the feature
     */
    @JsName("GN_POP")
    val population: Int

    /**
     * Whether the feature is a "world city". That seems to mean.. relevant to the world? idk..
     */
    @JsName("WORLDCITY")
    val isWorldCity: Double
}

class CityGuesserViewModel(
    private val citiesRepository: CitiesRepository = CitiesRepository()
) {
    var state by mutableStateOf(CityGuesserAppState.initial())
        private set

    private var alreadyGuessedCities = mutableListOf<String>()

    private data class RawQuizLocation(
        val quizLocation: QuizLocation,
        val rawValue: Feature
    )

    suspend fun loadCities() {
        val allCities = citiesRepository.getCities()
        val filteredFeatures = allCities.features.filter { feature ->
            val properties = feature.properties.unsafeCast<CityGuesserFeatureProperties>()
            if (state.level < 10) {
                properties.isWorldCity > 0
            } else if (state.level in 10..19) {
                properties.minZoom < 3
            } else if (state.level in 20..29) {
                properties.isMegacity > 0
            } else if (state.level in 30..39) {
                properties.population > 3_000_000
            } else if (state.level in 40..49) {
                properties.population > 2_000_000
            } else if (state.level in 50..59) {
                properties.minZoom < 5
            } else {
                properties.minZoom < 12
            }
        }

        val candidateCities = filteredFeatures
            .filter { it.properties.unsafeCast<CityGuesserFeatureProperties>().name !in alreadyGuessedCities }
            .toMutableList()

        val fourPlaces = buildList {
            repeat(4) {
                val randomPlace = candidateCities.random()
                add(randomPlace)
                candidateCities.remove(randomPlace)
            }
        }.map { location ->
            val geometry = location.geometry as Point
            val properties = location.properties.unsafeCast<CityGuesserFeatureProperties>()
            RawQuizLocation(
                quizLocation = QuizLocation(
                    cityName = properties.name,
                    countryName = properties.country,
                    coords = geometry.coordinates.toList()
                ),
                rawValue = location
            )
        }

        val correctLocation = fourPlaces.random()
        val correctLocationProperties = correctLocation.rawValue.properties.unsafeCast<CityGuesserFeatureProperties>()
        val worldCityScore = correctLocationProperties.isWorldCity
        val scaleRankScore = correctLocationProperties.minZoom
        val newScore = when (state.level) {
            0 -> 0
            else -> 100 * (5 + 5 * (1 - worldCityScore.toInt()) + scaleRankScore)
        }

        val displayablePlaces = fourPlaces.map(RawQuizLocation::quizLocation)

        state = state.copy(
            level = state.level + 1,
            score = state.score + newScore,
            quizState = Lce.Content(
                QuizState(
                    locations = displayablePlaces,
                    correctLocation = correctLocation.quizLocation,
                    answerCorrect = null
                )
            )
        )
    }

    suspend fun verifyAnswer(answer: QuizLocation) {
        val quizState = state.quizState
        require(quizState is Lce.Content)
        val answerCorrect = answer == quizState.data.correctLocation
        state = state.copy(
            quizState = quizState.copy(
                quizState.data.copy(
                    answerCorrect = answerCorrect
                )
            )
        )
        if (answerCorrect) {
            alreadyGuessedCities += quizState.data.correctLocation.cityName
        } else {
            state = CityGuesserAppState.initial()
        }
        loadCities()
    }
}
