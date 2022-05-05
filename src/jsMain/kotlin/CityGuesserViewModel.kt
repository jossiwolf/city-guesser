import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import common.Lce

class CityGuesserViewModel {
    var state by mutableStateOf(CityGuesserAppState.initial())
        private set

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
        } as Array<Any>

        val candidateCities = filteredCities.toMutableList()

        val fourPlaces = buildList<dynamic> {
            repeat(4) {
                val randomPlace = candidateCities.random()
                add(randomPlace)
                candidateCities.remove(randomPlace)
            }
        }.map { location ->
            QuizLocation(
                cityName = location.properties["NAME"] as String,
                coords = location.geometry.coordinates as Array<Double>
            )
        }
        state = state.copy(
            quizState = Lce.Content(
                QuizState(
                    locations = fourPlaces,
                    correctLocation = fourPlaces.random(),
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
            loadCities()
        }
    }
}