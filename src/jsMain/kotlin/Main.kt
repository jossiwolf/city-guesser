import androidx.compose.runtime.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import common.Lce
import kotlinx.browser.window
import mapbox.*
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.HTMLButtonElement

@Composable
fun WithWindowDensity(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDensity provides Density(window.devicePixelRatio.toFloat())) {
        content()
    }
}

@Composable
fun WithLayoutDirection(content: @Composable () -> Unit) {
    // Todo: Figure out how to get the layout direction
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        content()
    }
}

@Composable
fun WithViewConfiguration(
    density: Density = LocalDensity.current,
    configuration: ViewConfiguration = remember { JsViewConfiguration(density) },
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalViewConfiguration provides configuration) {
        content()
    }
}

@Composable
fun WebCompat(content: @Composable () -> Unit) {
    WithWindowDensity {
        WithViewConfiguration {
            WithLayoutDirection {
                content()
            }
        }
    }
}

fun main() {
    val viewModel = CityGuesserViewModel()
    renderComposable(rootElementId = "wrapper") {
        LaunchedEffect(viewModel) { viewModel.loadCities() }
        WebCompat {
            CityGuesserApp(
                state = viewModel.state,
                answerSubmitted = viewModel::verifyAnswer
            )
        }
    }
}

@Composable
inline fun <T> LceDataView(
    state: Lce<T>,
    loading: @Composable () -> Unit = { Progress() },
    content: @Composable (data: T) -> Unit
) {
    when (state) {
        is Lce.Loading -> loading()
        is Lce.Content -> content(state.data)
    }
}

@Composable
fun CityGuesserApp(
    state: CityGuesserAppState,
    answerSubmitted: (selectedAnswer: QuizLocation) -> Unit
) {
    Div {
        LceDataView(state = state.quizState) { quizState ->
            val mapState = rememberMapboxMapState(center = quizState.correctLocation.asMapLocation())
            Quiz(quizState, answerSubmitted)
            when (quizState.answerCorrect) {
                true -> Text("WHOHOO")
                false -> Text("BOOOOH")
            }
            MapboxMap(mapState)
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
            RadioInput(location.cityName) {
                onChange { answerSelected(location) }
            }
            Text(location.cityName)
            Br()
        }
    }
}

@Composable
fun Quiz(quizState: QuizState, answerSubmitted: (selectedAnswer: QuizLocation) -> Unit) = Div(
    attrs = { id("section") }
) {
    var selectedLocation by remember { mutableStateOf<QuizLocation?>(null) }
    AnswerOptions(
        locations = quizState.locations,
        selectedLocation = selectedLocation,
        answerSelected = { location -> selectedLocation = location }
    )
    Button(
        onClick = {
            val loc = selectedLocation
            if (loc != null) {
                answerSubmitted(loc)
            }
        }
    ) {
        Text("Submit Answer")
    }
}

@Composable
fun Button(
    onClick: () -> Unit,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null
) = Button(
    attrs = {
        //attrs?.invoke(this)
        onClick { onClick() }
    },
    content = content
)