package mapbox

import androidx.compose.runtime.Stable

@Stable
data class Location(val lng: Double, val lat: Double) {
    fun jsCompatible() = arrayOf(lng, lat)
}