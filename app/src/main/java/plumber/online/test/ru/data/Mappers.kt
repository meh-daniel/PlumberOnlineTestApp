package plumber.online.test.ru.data

import plumber.online.test.ru.data.sw.model.LocationSW
import plumber.online.test.ru.domain.model.Coordinates
import plumber.online.test.ru.domain.model.Location

internal fun LocationSW.toDomain(): Location {
    return Location(
        address = address,
        coordinates = Coordinates(
            lat = lat,
            lon = lon
        )
    )
}

internal fun Location.toSW(): LocationSW {
    return LocationSW(
        address = address,
        lat = coordinates.lat,
        lon = coordinates.lon,
    )
}