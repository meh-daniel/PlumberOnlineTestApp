package plumber.online.test.ru.presentation.screens.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import plumber.online.test.ru.R
import plumber.online.test.ru.databinding.MapFragmentBinding
import plumber.online.test.ru.presentation.base.BaseFragment

@AndroidEntryPoint
class MapFragment: BaseFragment<MapViewModel, MapFragmentBinding>(R.layout.map_fragment), InputListener {

    private lateinit var mapView: MapView
    private val mapAPI = "66dfa1d3-ba90-4c58-ba0c-d26629b56a76"
    private var userInput: Point = Point(0.0, 0.0)
    override val viewModel: MapViewModel by viewModels()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): MapFragmentBinding = MapFragmentBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey(mapAPI)
        MapKitFactory.initialize(this@MapFragment.requireContext())
    }

    override fun initialize() {

        mapView = binding.mapView
        val mapKit = MapKitFactory.getInstance()
        mapKit.resetLocationManagerToDefault()
        mapView.map.addInputListener(this)
    }


    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onMapTap(map: Map, point: Point) {
        mapView.map.mapObjects.clear()
        mapView.map.mapObjects.addPlacemark(point, ImageProvider.fromResource(this@MapFragment.requireContext(), R.drawable.www))
        userInput = point
    }


    override fun onMapLongTap(p0: Map, p1: Point) {

    }


}