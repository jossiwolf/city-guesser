package app

import androidx.compose.runtime.*
import common.Button
import common.FlexColumn
import common.FlexRow
import common.lce.LceDataView
import mapbox.*
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLButtonElement

@Composable
fun CityGuesserApp(
    state: CityGuesserAppState,
    answerSubmitted: (selectedAnswer: QuizLocation) -> Unit,
    playAgain: () -> Unit
) {
    LceDataView(state = state.quizState) { quizState ->
        FlexRow {
            val mapState = rememberMapboxMapState(
                center = quizState.correctLocation.asMapLocation(),
                maxBounds = quizState.correctLocation.bounds
            )
            val navigationControl = remember { MapboxGl.NavigationControl() }
            val scaleControl = remember { ScaleControl() }
            MapboxMap(
                state = mapState,
                onMapReady = { map ->
                    map.addControl(navigationControl, NavigationControlPosition.TopStart)
                    map.addControl(scaleControl)
                    map.dragRotateOptions.pitchWithRotate = false
                }
            )
            FlexColumn(
                attrs = {
                    style {
                        padding(12.px)
                    }
                }
            ) {
                H3 { Text("City-Guesser") }
                Br()
                Text("Level ${state.level}")
                Br()
                Text("Score ${state.score}")
                Br()
                Br()
                Quiz(
                    quizState = quizState,
                    submitAnswer = answerSubmitted,
                    playAgain = playAgain
                )
                Br()
                Text("Your Highscore: ${state.highScore}")
                Br()
                Br()
                Attribution()
            }
        }
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun AnswerOptions(
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

@Composable
private fun Quiz(
    quizState: QuizState,
    submitAnswer: (selectedAnswer: QuizLocation) -> Unit,
    playAgain: () -> Unit
) {
    var selectedLocation by remember { mutableStateOf<QuizLocation?>(null) }
    AnswerOptions(
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
        false -> {
            Text("Wrong answer! The correct answer was ${quizState.correctLocation.cityName}, ${quizState.correctLocation.countryName}.")
            CityGuesserButton(onClick = playAgain) {
                Text("Play again!")
            }
        }
    }
}

@Composable
private fun CityGuesserButton(
    onClick: () -> Unit,
    content: @Composable ElementScope<HTMLButtonElement>.() -> Unit
) {
    Button(
        onClick = onClick,
        attrs = {
            style {
                background("#FF8082")
                border {
                    style = LineStyle.Solid
                    width = 1.px
                    color = Color.white
                }
                color(Color.white)
                padding(10.px, 14.px)
                cursor("pointer")
            }
        },
        content = content
    )
}

@Composable
private fun Attribution() {
    Div(
        attrs = {
            style {
                fontSize(12.px)
                padding(8.px)
                border(1.px, LineStyle.Solid, Color.lightgray)
                background("#FCFAF2")
            }
        }
    ) {
        Text("Source code for this map is on ")
        A(href = "https://github.com/jossiwolf/CityGuesserApp/") { Text("Github") }
        Br()
        Text(" It was built by ")
        A(href = "https://github.com/jossiwolf/") { Text("Jossi Wolf") }
        Text(" after ")
        A(href = "https://jamaps.github.io/") { Text("Jeff Allen's version.") }
    }
}