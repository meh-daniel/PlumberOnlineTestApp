package plumber.online.test.ru.presentation.screens.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import plumber.online.test.ru.R
import plumber.online.test.ru.databinding.MapFragmentBinding
import plumber.online.test.ru.domain.model.Coordinates
import plumber.online.test.ru.presentation.base.BaseFragment
import plumber.online.test.ru.presentation.screens.main.MainAction
import plumber.online.test.ru.presentation.utils.observeInLifecycle

@AndroidEntryPoint
class MapFragment: BaseFragment<MapViewModel, MapFragmentBinding>(R.layout.map_fragment), InputListener {

    private lateinit var mapView: MapView
    private var userInput: Point = Point(0.0, 0.0)
    override val viewModel: MapViewModel by viewModels()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): MapFragmentBinding = MapFragmentBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun setupSubscribers() {
        setupActionFlow()
    }

    private fun setupActionFlow() {
        viewModel.actionFlow.onEach { action ->
            with(binding){
                if(action is MapAction.RouteToMainScreen) {
                    findNavController().navigate(R.id.action_mapFragment_to_mainFragment)
                }
                if(action is MapAction.ShowError) {
                    context?.let {
                        AlertDialog
                            .Builder(it)
                            .setTitle("Error")
                            .setMessage(action.message)
                            .setNegativeButton("Ok") { _, _ -> }
                            .show()
                    }
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    override fun setupListeners() {
        binding.ivBack.setOnClickListener {
            viewModel.routeToMainScreen()
        }
        binding.sendUserCoordinatesBtn.setOnClickListener {
            Log.d("xxx123", "${
                Coordinates(
                    lat = userInput.latitude,
                    lon = userInput.longitude
                )
            }")
            viewModel.saveCoordinates(Coordinates(
                lat = userInput.latitude,
                lon = userInput.longitude
            ))
        }
    }

}