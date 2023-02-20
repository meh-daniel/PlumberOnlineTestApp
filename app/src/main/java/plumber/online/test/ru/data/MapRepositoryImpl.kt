package plumber.online.test.ru.data

import plumber.online.test.ru.data.sw.MapDataBase
import plumber.online.test.ru.domain.MapRepository
import plumber.online.test.ru.domain.model.Location

class MapRepositoryImpl(
    private val dataBase: MapDataBase
): MapRepository {

    override suspend fun save(location: Location) {
        TODO("Not yet implemented")
    }

    override suspend fun get(): Location {
        TODO("Not yet implemented")
    }

}