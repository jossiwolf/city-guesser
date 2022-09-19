package dom

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.w3c.dom.HTMLButtonElement

/**
 * Facade for the Button component, offering a more idiomatic API with an onClick param
 */
@Composable
@DomComposable
fun DomBasedButton(
    onClick: () -> Unit,
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLButtonElement>? = null
) = org.jetbrains.compose.web.dom.Button(
    attrs = {
        attrs?.invoke(this)
        onClick { onClick() }
    },
    content = content
)