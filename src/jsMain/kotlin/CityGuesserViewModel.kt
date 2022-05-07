import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import common.lce.Lce
import domain.cities

class CityGuesserViewModel {
    var state by mutableStateOf(CityGuesserAppState.initial())
        private set

    private var alreadyGuessedCities = mutableListOf<String>()

    private data class RawQuizLocation(
        val quizLocation: QuizLocation,
        val rawValue: dynamic
    )

    fun loadCities() {
        val filteredCities = cities.asDynamic().features.filter { feature ->
            val properties = feature.properties
            if (state.level < 10) {
                properties["WORLDCITY"] > 0
            } else if (state.level in 10..19) {
                properties["SCALERANK"] < 3
            } else if (state.level in 20..29) {
                properties["MEGACITY"] > 0
            } else if (state.level in 30..39) {
                properties["GN_POP"] > 3_000_000
            } else if (state.level in 40..49) {
                properties["GN_POP"] > 2_000_000
            } else if (state.level in 50..59) {
                properties["SCALERANK"] < 5
            } else {
                properties["SCALERANK"] < 12
            }
        } as Array<dynamic>

        val candidateCities = filteredCities
            .filter { it.properties["NAME"] as String !in alreadyGuessedCities }
            .toMutableList()

        val fourPlaces = buildList<dynamic> {
            repeat(4) {
                val randomPlace = candidateCities.random()
                add(randomPlace)
                candidateCities.remove(randomPlace)
            }
        }.map { location ->
            RawQuizLocation(
                quizLocation = QuizLocation(
                    cityName = location.properties["NAME"] as String,
                    coords = location.geometry.coordinates as Array<Double>
                ),
                rawValue = location
            )
        }

        val correctLocation = fourPlaces.random()
        val worldCityScore = correctLocation.rawValue.properties["WORLDCITY"] as Number
        val scaleRankScore = correctLocation.rawValue.properties["SCALERANK"] as Number
        val newScore = when (state.level) {
            0 -> 0
            else -> 100 * (5 + 5 * (1 - worldCityScore.toInt()) + scaleRankScore.toInt())
        }

        val displayablePlaces = fourPlaces.map(RawQuizLocation::quizLocation)

        state = state.copy(
            level = state.level + 1, // Simple assumption but works for now lol,
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

    fun verifyAnswer(answer: QuizLocation) {
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
