package dom.mapbox

import androidx.compose.runtime.Stable


@Stable
@JsName("MapboxMapOptions")
class MapboxMapOptions(
    @JsName("container") val container: String,
    val style: String,
    val center: Array<Double>,
    val zoom: Double = 11.5,
    val minZoom: Double = 11.0,
    val maxZoom: Double = 16.0,
    val maxBounds: List<List<Double>>
)

enum class NavigationControlPosition(val mapboxName: String) {
    TopStart("top-left"), TopEnd("top-right"), BottomStart("bottom-left"), BottomEnd("bottom-right")
}

external interface ScaleControlOptions {
    val maxWidth: Int
    val unit: String
}

fun DefaultScaleControlOptions() = object : ScaleControlOptions {
    override val maxWidth: Int = 100
    override val unit: String = "metric"
}

@JsName("mapboxgl")
external object MapboxGl {
    var accessToken: String

    @JsName("Map")
    class Map(options: dynamic) {

        @JsName("dragRotate")
        val dragRotateOptions: DragRotate

        fun remove()
        fun setCenter(center: Array<Double>)
        fun addControl(control: MapControl, position: String)
        fun addControl(control: MapControl)

        interface DragRotate {
            @JsName("_pitchWithRotate")
            var pitchWithRotate: Boolean
        }
    }

    class FitBoundsOptions

    class MapboxOptions

    class TransformRequestFunction

    interface MapControl

    class NavigationControl : MapControl

    class ScaleControl(options: ScaleControlOptions) : MapControl
}

fun ScaleControl(options: ScaleControlOptions = DefaultScaleControlOptions()) = MapboxGl.ScaleControl(options)

fun MapboxGl.Map.addControl(
    control: MapboxGl.MapControl, position: NavigationControlPosition
) = addControl(control, position.mapboxName)