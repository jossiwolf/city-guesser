package common.lce

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Progress

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