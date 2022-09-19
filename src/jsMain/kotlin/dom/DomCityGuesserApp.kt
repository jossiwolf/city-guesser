package dom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import app.CityGuesserAppState
import app.QuizLocation
import app.asMapLocation
import common.FlexColumn
import common.FlexRow
import dom.mapbox.*
import lce.LceDataView
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@DomComposable
@Composable
fun DomCityGuesserApp(
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
                DomBasedQuiz(
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
