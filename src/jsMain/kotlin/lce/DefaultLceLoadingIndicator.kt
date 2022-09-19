package lce

import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import common.isDomApp
import org.jetbrains.compose.web.dom.Progress

actual val DefaultLceLoadingIndicator: @Composable () -> Unit = @Composable {
    if(isDomApp()) Progress() else LinearProgressIndicator()
}