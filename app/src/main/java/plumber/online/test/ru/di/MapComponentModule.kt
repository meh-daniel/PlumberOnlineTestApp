package plumber.online.test.ru.di

import android.content.Context
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import plumber.online.test.ru.data.MapRepositoryImpl
import plumber.online.test.ru.data.sw.MapDataBase
import plumber.online.test.ru.domain.MapRepository
import javax.inject.Singleton

class MapComponentModule {

    @Provides
    @Singleton
    fun createMapDataBase(
        @ApplicationContext context: Context
    ): MapDataBase {
        return MapDataBase.invoke(context)
    }

    @Provides
    @Singleton
    fun provideMapRepository(
        dataBase: MapDataBase
    ) : MapRepository {
        return MapRepositoryImpl(dataBase)
    }

}