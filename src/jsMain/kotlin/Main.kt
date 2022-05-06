import androidx.compose.runtime.*
import common.Button
import common.WebCompat
import common.lce.Lce
import common.lce.LceDataView
import common.renderWebCompatComposable
import mapbox.MapboxMap
import mapbox.rememberMapboxMapState
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

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
        val mapState = rememberMapboxMapState(center = quizState.correctLocation.asMapLocation())
        Text("Level ${state.level}")
        Br()
        Quiz(quizState, answerSubmitted)
        when (quizState.answerCorrect) {
            true -> Text("WHOHOO")
            false -> Text("BOOOOH")
        }
        MapboxMap(mapState)
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
            RadioInput(location.cityName) {
                onChange { answerSelected(location) }
            }
            Text(location.cityName)
            Br()
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
    Button(onClick = { selectedLocation?.let(answerSubmitted) }) {
        Text("Submit Answer")
    }
}