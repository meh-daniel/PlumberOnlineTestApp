package plumber.online.test.ru.data

import plumber.online.test.ru.data.sw.model.CoordinatesSW
import plumber.online.test.ru.domain.model.Coordinates

internal fun CoordinatesSW.toDomain(): Coordinates {
    return Coordinates(
        lat = lat,
        lon = lon
    )
}

internal fun Coordinates.toSW(): CoordinatesSW {
    return CoordinatesSW(
        lat = lat,
        lon = lon
    )
}