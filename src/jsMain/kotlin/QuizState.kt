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
    val coords: Array<Double>
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