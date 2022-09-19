package domain

import io.data2viz.geojson.FeatureCollection
import io.data2viz.geojson.js.asGeoJsonObject

@JsName("c_CitiesRepository")
actual fun CitiesRepository(): CitiesRepository = AssetBasedCitiesRepository()

internal class AssetBasedCitiesRepository: CitiesRepository {
    override suspend fun getCities(): FeatureCollection {
        return cities.asGeoJsonObject() as FeatureCollection
    }
}
