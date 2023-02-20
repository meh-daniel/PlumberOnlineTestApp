package plumber.online.test.ru.domain

import plumber.online.test.ru.domain.model.Location

interface MapRepository {

    suspend fun save(location: Location)

    suspend fun get(): Location

}