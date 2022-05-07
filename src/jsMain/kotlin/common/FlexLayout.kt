package common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.dom.Div

interface FlexLayoutScope

class HtmlFlexLayoutScope: FlexLayoutScope

@Composable
fun FlexLayout(
    flexDirection: FlexDirection,
    content: @Composable FlexLayoutScope.() -> Unit
) {
    Div(
        attrs = {
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
fun FlexRow(content: @Composable FlexLayoutScope.() -> Unit) {
    FlexLayout(flexDirection = FlexDirection.Row, content)
}

@Composable
fun FlexColumn(content: @Composable FlexLayoutScope.() -> Unit) {
    FlexLayout(flexDirection = FlexDirection.Column, content)
}