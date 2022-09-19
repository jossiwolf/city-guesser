package common

import androidx.compose.runtime.Composable
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.Element
import org.w3c.dom.get

/**
 * Helper for [renderComposable] entry point that checks for the existence of the root element and throws a meaningful
 * exception otherwise.
 */
fun renderComposable(
    rootElementId: String,
    content: @Composable DOMScope<Element>.() -> Unit
) {
    // We are throwing a weird exception when the root element isn't present in the DOM.. let's try to make it a bit nicer!
    requireRootElementHasId(rootElementId)
    renderComposable(rootElementId, content)
}

fun requireRootElementHasId(rootElementId: String) {
    val rootElement = window.document.getElementById(rootElementId)
    checkNotNull(rootElement) {
        val alternateRootElement = window.document.childNodes[0]
        val suggestion = alternateRootElement?.let { "Did you mean ${it.nodeName} instead?" }
        "Root element with id $rootElementId wasn't found. ${suggestion ?: ""}"
    }
}