package dom

import androidx.compose.runtime.*
import app.QuizLocation
import app.QuizState
import common.FlexColumn
import common.FlexRow
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.dom.*

@DomComposable
@Composable
fun DomBasedQuiz(
    quizState: QuizState,
    submitAnswer: (selectedAnswer: QuizLocation) -> Unit,
    playAgain: () -> Unit
) {
    FlexColumn {
        var selectedLocation by remember { mutableStateOf<QuizLocation?>(null) }
        DomBasedAnswerOptions(
            locations = quizState.locations,
            inputEnabled = quizState.answerCorrect != false,
            selectedLocation = selectedLocation,
            answerSelected = { location -> selectedLocation = location }
        )
        Br()
        when (quizState.answerCorrect) {
            null -> CityGuesserButton(onClick = { selectedLocation?.let(submitAnswer) }) {
                Text("Submit Answer")
            }

            true -> {}
            false -> {
                Text("Wrong answer! The correct answer was ${quizState.correctLocation.cityName}, ${quizState.correctLocation.countryName}.")
                CityGuesserButton(onClick = playAgain) {
                    Text("Play again!")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun DomBasedAnswerOptions(
    locations: List<QuizLocation>,
    inputEnabled: Boolean,
    selectedLocation: QuizLocation?,
    answerSelected: (location: QuizLocation) -> Unit
) {
    RadioGroup(checkedValue = selectedLocation?.cityName) {
        locations.forEach { location ->
            FlexRow {
                RadioInput(location.cityName) {
                    classes("location_input")
                    onChange { answerSelected(location) }
                    if (!inputEnabled) {
                        disabled()
                    }
                }
                B { Text(location.cityName) }
                Text(", ${location.countryName}")
            }
        }
    }
}
