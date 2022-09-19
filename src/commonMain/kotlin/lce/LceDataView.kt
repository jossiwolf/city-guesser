package lce

import androidx.compose.runtime.Composable

@Composable
expect fun DefaultLceLoadingIndicator()

@Composable
inline fun <T> LceDataView(
    state: Lce<T>,
    loading: @Composable () -> Unit = { DefaultLceLoadingIndicator() },
    content: @Composable (data: T) -> Unit
) {
    when (state) {
        is Lce.Loading -> loading()
        is Lce.Content -> content(state.data)
    }
}