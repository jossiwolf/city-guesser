package common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

interface FlexLayoutScope

class HtmlFlexLayoutScope: FlexLayoutScope

@Composable
fun FlexLayout(
    flexDirection: FlexDirection,
    attrs: AttrsScope<HTMLDivElement>.() -> Unit = { },
    content: @Composable FlexLayoutScope.() -> Unit
) {
    Div(
        attrs = {
            attrs()
            style {
                display(DisplayStyle.Flex)
                flexDirection(flexDirection)
            }
        }
    ) {
        val scope = remember { HtmlFlexLayoutScope() }
        scope.content()
    }
}

@Composable
fun FlexRow(
    attrs: AttrsScope<HTMLDivElement>.() -> Unit = {},
    content: @Composable FlexLayoutScope.() -> Unit
) {
    FlexLayout(attrs = attrs, flexDirection = FlexDirection.Row, content = content)
}

@Composable
fun FlexColumn(
    attrs: AttrsScope<HTMLDivElement>.() -> Unit = {},
    content: @Composable FlexLayoutScope.() -> Unit
) {
    FlexLayout(attrs = attrs, flexDirection = FlexDirection.Column, content = content)
}