import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import app.CityGuesserApp
import app.CityGuesserTheme
import app.CityGuesserViewModel
import kotlinx.coroutines.launch
import org.jetbrains.skiko.wasm.onWasmReady

fun main() = onWasmReady {
    val viewModel = CityGuesserViewModel()
    Window {
        CityGuesserTheme {
            val scope = rememberCoroutineScope()
            LaunchedEffect(viewModel) { viewModel.loadCities() }
            CityGuesserApp(
                state = viewModel.state,
                answerSubmitted = { answer -> scope.launch { viewModel.verifyAnswer(answer) } },
                playAgain = { scope.launch { viewModel.playAgain() } }
            )
        }
    }
}
