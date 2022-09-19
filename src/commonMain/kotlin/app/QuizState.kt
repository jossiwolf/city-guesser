package app

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import lce.Lce
import data.Location

@Stable
data class QuizState(
    val locations: List<QuizLocation>,
    val correctLocation: QuizLocation,
    val answerCorrect: Boolean?
)

@Stable
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
    val highScore: Int,
    val quizState: Lce<QuizState>
) {
    companion object {
        fun initial() = CityGuesserAppState(level = 0, quizState = Lce.Loading, score = 0, highScore = 0)
    }
}

fun QuizLocation.asMapLocation() = Location(lng = coords[0], lat = coords[1])
fun QuizLocation.formattedName() = buildAnnotatedString {
    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
    append(cityName)
    pop()
    append(", ")
    append(countryName)
}