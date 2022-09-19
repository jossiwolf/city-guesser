package dom.mapbox

import data.Location


fun Location.jsCompatible() = arrayOf(lng, lat)