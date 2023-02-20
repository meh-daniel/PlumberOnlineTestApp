package plumber.online.test.ru

import android.app.Application
import android.content.Context
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

private const val MAP_KIT_API_KEY = "66dfa1d3-ba90-4c58-ba0c-d26629b56a76"

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initializedMapKit(MAP_KIT_API_KEY, this)
    }

    private fun initializedMapKit(apiKey: String, context: Context) {
        MapKitFactory.setApiKey(apiKey)
        MapKitFactory.initialize(context)
    }

}