import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import common.Button
import common.FlexColumn
import common.FlexRow
import common.lce.LceDataView
import common.renderWebCompatComposable
import kotlinx.coroutines.launch
import mapbox.*
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

fun main() {
    val viewModel = CityGuesserViewModel()
    renderWebCompatComposable(rootElementId = "root") {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) { viewModel.loadCities() }
        CityGuesserApp(
            state = viewModel.state,
            answerSubmitted = { answer -> scope.launch { viewModel.verifyAnswer(answer) } }
        )
    }
}

@Composable
fun CityGuesserApp(
    state: CityGuesserAppState,
    answerSubmitted: (selectedAnswer: QuizLocation) -> Unit
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
                Quiz(quizState, answerSubmitted)
                when (quizState.answerCorrect) {
                    true -> Text("WHOHOO")
                    false -> Text("BOOOOH")
                }
                Br()
                Attribution()
            }
        }
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun AnswerOptions(
    locations: List<QuizLocation>,
    selectedLocation: QuizLocation?,
    answerSelected: (location: QuizLocation) -> Unit
) {
    RadioGroup(checkedValue = selectedLocation?.cityName) {
        locations.forEach { location ->
            FlexRow {
                RadioInput(location.cityName) {
                    classes("location_input")
                    onChange { answerSelected(location) }
                }
                B { Text(location.cityName) }
                Text(", ${location.countryName}")
            }
        }
    }
}

@Composable
fun Quiz(quizState: QuizState, answerSubmitted: (selectedAnswer: QuizLocation) -> Unit) {
    var selectedLocation by remember { mutableStateOf<QuizLocation?>(null) }
    AnswerOptions(
        locations = quizState.locations,
        selectedLocation = selectedLocation,
        answerSelected = { location -> selectedLocation = location }
    )
    Br()
    Button(
        onClick = { selectedLocation?.let(answerSubmitted) },
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
        }
    ) {
        Text("Submit Answer")
    }
}

@Composable
fun Attribution() {
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
        A(href ="https://github.com/jossiwolf/CityGuesserApp/") { Text("Github") }
        Br()
        Text(" It was built by ")
        A(href = "https://github.com/jossiwolf/") { Text("Jossi Wolf") }
        Text(" after ")
        A(href = "https://jamaps.github.io/") { Text("Jeff Allen's version.") }
    }
}