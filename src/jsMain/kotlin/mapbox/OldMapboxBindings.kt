package mapbox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember


@Stable
@JsName("MapboxMapOptions")
class MapboxMapOptions(
    @JsName("container")
    val container: String,
    val style: String,
    val center: Array<Double>,
    val zoom: Double = 11.5,
    val minZoom: Double = 11.0,
    val maxZoom: Double = 16.0,
    val maxBounds: List<List<Double>>
)

@JsName("mapboxgl")
external object MapboxGl {
    var accessToken: String

    @JsName("Map")
    class Map(options: dynamic) {
        fun remove()
        fun setCenter(center: Array<Double>)
    }

    class FitBoundsOptions

    class MapboxOptions

    class TransformRequestFunction
}
