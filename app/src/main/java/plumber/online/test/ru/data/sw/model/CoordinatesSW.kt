package plumber.online.test.ru.data.sw.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = CoordinatesSW.TABLE_NAME
)
data class CoordinatesSW(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    val lat: Double,
    val lon: Double,
) {
    companion object {
        const val TABLE_NAME = "location"
    }
}
