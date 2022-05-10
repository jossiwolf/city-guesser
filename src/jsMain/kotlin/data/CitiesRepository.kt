package data

import domain.cities
import io.data2viz.geojson.FeatureCollection
import io.data2viz.geojson.js.asGeoJsonObject

interface CitiesRepository {
    suspend fun getCities(): FeatureCollection
}

internal class AssetBasedCitiesRepository: CitiesRepository {
    override suspend fun getCities(): FeatureCollection {
        return cities.asGeoJsonObject() as FeatureCollection
    }
}

@JsName("c_CitiesRepository")
fun CitiesRepository(): CitiesRepository = AssetBasedCitiesRepository()