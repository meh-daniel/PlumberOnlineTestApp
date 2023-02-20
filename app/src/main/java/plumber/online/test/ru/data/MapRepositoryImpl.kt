package plumber.online.test.ru.data

import plumber.online.test.ru.data.sw.MapDataBase
import plumber.online.test.ru.domain.MapRepository
import plumber.online.test.ru.domain.model.Coordinates

class MapRepositoryImpl(
    private val dataBase: MapDataBase
): MapRepository {

    override suspend fun save(location: Coordinates) {
        dataBase.mapDao().updateCurrentLocation(location.toSW())
    }

    override suspend fun get(): Coordinates {
        return dataBase.mapDao().getCurrentLocation().toDomain()
    }

}