package dom

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLButtonElement

@DomComposable
@Composable
fun CityGuesserButton(
    onClick: () -> Unit,
    content: @Composable ElementScope<HTMLButtonElement>.() -> Unit
) {
    DomBasedButton(
        onClick = onClick,
        attrs = {
            style {
                background("#FF8082")
                border {
                    style = LineStyle.Solid
                    width = 1.px
                    color = Color.white
                }
                color(Color.white)
                padding(10.px, 14.px)
                cursor("pointer")
            }
        },
        content = content
    )
}