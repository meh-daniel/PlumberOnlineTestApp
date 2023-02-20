package plumber.online.test.ru.data.sw.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = LocationSW.TABLE_NAME,
    indices = [
        Index("ad", unique = true )
    ]
)
data class LocationSW(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val address: Int,
    val lat: Double,
    val lon: Double,
) {
    companion object {
        const val TABLE_NAME = "location"
    }
}
