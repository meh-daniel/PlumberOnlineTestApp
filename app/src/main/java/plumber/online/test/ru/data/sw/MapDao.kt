package plumber.online.test.ru.data.sw

import androidx.room.*
import plumber.online.test.ru.data.sw.model.CoordinatesSW

@Dao
interface MapDao {

    @Insert
    suspend fun insertLocation(location: CoordinatesSW)

    @Query("DELETE FROM ${CoordinatesSW.TABLE_NAME}")
    suspend fun deleteCurrentMap()

    @Query("SELECT * FROM ${CoordinatesSW.TABLE_NAME}")
    suspend fun getCurrentLocation() : CoordinatesSW

    @Transaction
    suspend fun updateCurrentLocation(coordinates: CoordinatesSW) {
        deleteCurrentMap()
        insertLocation(coordinates)
    }

}