package domain

import io.data2viz.geojson.FeatureCollection

interface CitiesRepository {
    suspend fun getCities(): FeatureCollection
}

expect fun CitiesRepository(): CitiesRepository