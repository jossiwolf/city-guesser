package dom.mapbox

import androidx.compose.runtime.*
import androidx.compose.ui.layout.Layout
import kotlinx.browser.document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.attributes.AttrsScopeBuilder
import org.jetbrains.compose.web.css.StyleHolder
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.StyleScopeBuilder
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.internal.runtime.*
import org.jetbrains.compose.web.renderComposable
import org.jetbrains.compose.web.renderComposableInBody
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.Node
import org.w3c.dom.css.ElementCSSInlineStyle
import org.w3c.dom.svg.SVGElement

@Composable
fun MapboxMap(state: MapboxMapState, onMapReady: (map: MapboxGl.Map) -> Unit) {
    // We should update the center instead of recreating the whole map here
    DisposableEffect(state, onMapReady) {
        MapboxGl.accessToken =
            "pk.eyJ1IjoiamVmZmFsbGVuIiwiYSI6ImNrbGgyMXFjaDB6aXoyd29pNmF4NTRyMWwifQ.vwE_3WfPjbTW8cMoDKbq6A"
        val map = state.createMap("map")
        state.attach(map)
        onMapReady(state.map)
        onDispose {
            map.remove()
        }
    }
    Div(attrs = {
        id("map")
        style {
            /*top(0.px)
            bottom(0.px)
            width(70.percent)
            height(100.vh)
            backgroundColor(Color.black)
            border {
                style(LineStyle.Solid)
                color(Color.darkgray)
                right(1.px)
            }*/
        }
    }, content = { })

}