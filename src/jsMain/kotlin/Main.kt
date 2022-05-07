import androidx.compose.runtime.*
import common.Button
import common.FlexColumn
import common.FlexRow
import common.lce.LceDataView
import common.renderWebCompatComposable
import mapbox.MapboxMap
import mapbox.rememberMapboxMapState
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

fun main() {
    val viewModel = CityGuesserViewModel()
    renderWebCompatComposable(rootElementId = "root") {
        LaunchedEffect(viewModel) { viewModel.loadCities() }
        CityGuesserApp(
            state = viewModel.state,
            answerSubmitted = viewModel::verifyAnswer
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
            val mapState = rememberMapboxMapState(center = quizState.correctLocation.asMapLocation())
            MapboxMap(mapState)
            FlexColumn {
                H3 { Text("City-Guesser") }
                Text("Level ${state.level}")
                Br()
                Text("Score ${state.score}")
                Quiz(quizState, answerSubmitted)
                when (quizState.answerCorrect) {
                    true -> Text("WHOHOO")
                    false -> Text("BOOOOH")
                }
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
                Text(location.cityName)
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