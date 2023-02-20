package plumber.online.test.ru.data.sw

import androidx.room.*
import plumber.online.test.ru.data.sw.model.LocationSW

@Dao
interface MapDao {

    @Insert
    suspend fun insertLocation(location: LocationSW)

    @Query("DELETE FROM ${LocationSW.TABLE_NAME}")
    suspend fun deleteCurrentMap()

    @Query("SELECT * FROM ${LocationSW.TABLE_NAME}")
    suspend fun getCurrentLocation() : LocationSW

    @Transaction
    suspend fun updateCurrentLocation(location: LocationSW) {
        deleteCurrentMap()
        insertLocation(location)
    }

}