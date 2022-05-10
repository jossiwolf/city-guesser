package app

import androidx.compose.runtime.Stable
import common.lce.Lce
import mapbox.Location

@Stable
data class QuizState(
    val locations: List<QuizLocation>,
    val correctLocation: QuizLocation,
    val answerCorrect: Boolean?
)

data class QuizLocation(
    val cityName: String,
    val countryName: String,
    val coords: List<Double>,
    val bounds: List<List<Double>> = listOf(
        listOf(coords[0] - 0.42, coords[1] + 0.42),
        listOf(coords[0] + 0.42, coords[1] - 0.42)
    )
)

@Stable
data class CityGuesserAppState(
    val level: Int,
    val score: Int,
    val quizState: Lce<QuizState>
) {
    companion object {
        fun initial() = CityGuesserAppState(level = 0, quizState = Lce.Loading, score = 0)
    }
}

fun QuizLocation.asMapLocation() = Location(lng = coords[0], lat = coords[1])