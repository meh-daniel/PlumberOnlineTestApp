package plumber.online.test.ru.domain

import plumber.online.test.ru.domain.model.Coordinates

interface MapRepository {

    suspend fun save(location: Coordinates)

    suspend fun get(): Coordinates

}