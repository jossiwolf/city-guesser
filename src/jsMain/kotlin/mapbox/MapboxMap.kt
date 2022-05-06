package mapbox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import org.jetbrains.compose.web.dom.Div

@Composable
fun MapboxMap(state: MapboxMapState) {
    // We should update the center instead of recreating the whole map here
    DisposableEffect(state) {
        MapboxGl.accessToken =
            "pk.eyJ1IjoiamVmZmFsbGVuIiwiYSI6ImNrbGgyMXFjaDB6aXoyd29pNmF4NTRyMWwifQ.vwE_3WfPjbTW8cMoDKbq6A"
        val map = state.createMap("map")
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