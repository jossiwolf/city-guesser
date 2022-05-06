package common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.Element
import org.w3c.dom.get

fun renderWebCompatComposable(
    rootElementId: String,
    content: @Composable DOMScope<Element>.() -> Unit
) {
    // We are throwing a weird exception when the root element isn't present in the DOM.. let's try to make it a bit nicer!
    val rootElement = window.document.getElementById(rootElementId)
    checkNotNull(rootElement) {
        val alternateRootElement = window.document.childNodes[0]
        val suggestion = alternateRootElement?.let { "Did you mean ${it.nodeName} instead?" }
        "Root element with id $rootElementId wasn't found. ${suggestion ?: ""}"
    }
    renderComposable(rootElementId) {
        WebCompat { content() }
    }
}

/**
 * Provides values for all kinds of different composition locals
 */
@Composable
inline fun WebCompat(crossinline content: @Composable () -> Unit) {
    WithWindowDensity {
        WithViewConfiguration {
            WithLayoutDirection {
                content()
            }
        }
    }
}

@Composable
inline fun WithWindowDensity(crossinline content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDensity provides Density(window.devicePixelRatio.toFloat())) {
        content()
    }
}

@Composable
inline fun WithLayoutDirection(crossinline content: @Composable () -> Unit) {
    // Todo: Figure out how to get the layout direction
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        content()
    }
}

@Composable
inline fun WithViewConfiguration(
    density: Density = LocalDensity.current,
    configuration: ViewConfiguration = remember { JsViewConfiguration(density) },
    crossinline content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalViewConfiguration provides configuration) {
        content()
    }
}