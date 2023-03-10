package plumber.online.test.ru.data.sw

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import plumber.online.test.ru.data.sw.model.CoordinatesSW

@Database(
    entities = arrayOf(
        CoordinatesSW::class
    ),
    version = MapDataBase.DB_VERSION,
    exportSchema = false,
)
abstract class MapDataBase: RoomDatabase() {
    companion object {
        const val DB_VERSION = 2
        private const val DB_NAME = "coordinates.db"

        @Volatile private var instance : MapDataBase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MapDataBase::class.java,
            DB_NAME
        ).build()
    }
    abstract fun mapDao(): MapDao
}
