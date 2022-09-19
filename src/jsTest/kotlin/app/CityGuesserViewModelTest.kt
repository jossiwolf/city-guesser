package app

import io.data2viz.geojson.Geometry
import io.data2viz.geojson.Position
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CityGuesserViewModelTest {

    @Test
    fun test_cities() = runTest {
        val viewModel = CityGuesserViewModel()
        val city = Stub.City(lng = 0.0, lat = 0.0)

        viewModel.getCitiesForLevel(arrayOf(city), 0)
    }

}

interface Stub {
    companion object : Stub
}

fun Stub.Point(
    position: Position
) = io.data2viz.geojson.Point(position)

fun Stub.Position(
    lng: Double,
    lat: Double
) = doubleArrayOf(lng, lat)

fun Stub.Feature(
    geometry: Geometry,
    id: Any? = null,
    properties: Any? = null
) = io.data2viz.geojson.Feature(
    geometry = geometry,
    id = id,
    properties = properties
)

fun Stub.City(
    lng: Double,
    lat: Double,
    id: Any? = null,
    properties: Any? = null
) = io.data2viz.geojson.Feature(
    geometry = Stub.Point(Stub.Position(lng = lng, lat = lat)),
    id = id,
    properties = properties
)