import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import app.CityGuesserApp
import app.CityGuesserFeatureFlags
import app.CityGuesserTheme
import app.CityGuesserViewModel
import common.renderComposable
import dom.DomBasedQuiz
import dom.DomCityGuesserApp
import kotlinx.coroutines.launch
import org.jetbrains.skiko.wasm.onWasmReady


fun main() {
    val featureFlags = CityGuesserFeatureFlags()
    val target = if (featureFlags.enableWasmCompose) ComposeJSTargetType.Skia else ComposeJSTargetType.DOM
    println("Running with target $target")
    ComposeApp(target) {
        val viewModel = CityGuesserViewModel()
        CityGuesserTheme {
            val scope = rememberCoroutineScope()
            LaunchedEffect(viewModel) { viewModel.loadCities() }
            if (target == ComposeJSTargetType.DOM) {
                DomCityGuesserApp(
                    state = viewModel.state,
                    answerSubmitted = { answer -> scope.launch { viewModel.verifyAnswer(answer) } },
                    playAgain = { scope.launch { viewModel.playAgain() } }
                )
            } else {
                CityGuesserApp(
                    state = viewModel.state,
                    answerSubmitted = { answer -> scope.launch { viewModel.verifyAnswer(answer) } },
                    playAgain = { scope.launch { viewModel.playAgain() } }
                )
            }
        }
    }
}

enum class ComposeJSTargetType {
    DOM,
    Skia
}

fun ComposeApp(target: ComposeJSTargetType, content: @Composable () -> Unit) {
    when (target) {
        ComposeJSTargetType.DOM -> renderComposable("root") {
            content()
        }

        ComposeJSTargetType.Skia -> onWasmReady { Window(content = content) }
    }
}