package mapbox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.jetbrains.compose.web.dom.Div

@Composable
fun MapboxMap(state: MapboxMapState) {
    LaunchedEffect(state) {
        MapboxGl.accessToken =
            "pk.eyJ1IjoiamVmZmFsbGVuIiwiYSI6ImNrbGgyMXFjaDB6aXoyd29pNmF4NTRyMWwifQ.vwE_3WfPjbTW8cMoDKbq6A"
        state.attach(state.createMap("map"))
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