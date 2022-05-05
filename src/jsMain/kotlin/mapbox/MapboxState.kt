package mapbox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

//import mapbox.mapbox.MapboxMapOptions

@Composable
fun rememberMapboxMapState(center: Location) = remember(center) {
    MapboxMapState(
        style = "mapbox://styles/jeffallen/cj8rwyt7obvqq2sr8ygoi9dh2",
        center = center.jsCompatible(),
        maxBounds = emptyList()
    )
}

@Stable
class MapboxMapState(
    val style: String,
    val center: Array<Double>,
    val zoom: Double = 11.5,
    val minZoom: Double = 11.0,
    val maxZoom: Double = 16.0,
    val maxBounds: List<List<Double>>
) {
    lateinit var map: MapboxGl.Map
    fun createMap(
        container: String,
        options: MapboxMapOptions = MapboxMapOptions(
            container = container,
            style = style,
            center = center,
            zoom = zoom,
            minZoom = minZoom,
            maxZoom = maxZoom,
            maxBounds = maxBounds
        )
    ) = MapboxGl.Map(
        jsObject {
            this.container = container
            this.style = style
            this.center = jsObject {
                this.lng = center[0]
                this.lat = center[1]
            }
            this.minZoom = minZoom
            this.maxZoom = maxZoom
            //this.maxBounds = maxBounds
        }
    )

    fun attach(map: MapboxGl.Map) {
        this.map = map
    }
}


inline fun jsObject(init: dynamic.() -> Unit): dynamic {
    val o = js("{}")
    init(o)
    return o
}