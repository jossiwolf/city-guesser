import androidx.compose.runtime.*
import app.CityGuesserApp
import app.CityGuesserViewModel
import common.renderWebCompatComposable
import kotlinx.coroutines.launch

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