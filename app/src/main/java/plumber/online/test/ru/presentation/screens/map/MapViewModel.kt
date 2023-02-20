package plumber.online.test.ru.presentation.screens.map

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import plumber.online.test.ru.domain.MapRepository
import plumber.online.test.ru.domain.model.Coordinates
import plumber.online.test.ru.presentation.base.BaseViewModel
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: MapRepository
): BaseViewModel() {

    private val _action: Channel<MapAction> = Channel(Channel.BUFFERED)
    var actionFlow = _action.receiveAsFlow()

    fun saveCoordinates(coordinates: Coordinates) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.save(coordinates)
                routeToMainScreen()
            } catch (e: Throwable) {
                when (e) {
                    is ConnectException -> sendAction(MapAction.ShowError("отсутствует интернет"))
                    else -> sendAction(MapAction.ShowError(e.message.toString()))
                }
            }
        }
    }

    fun routeToMainScreen() {
        sendAction(MapAction.RouteToMainScreen)
    }

    private fun sendAction(action: MapAction){
        viewModelScope.launch(Dispatchers.Main){
            _action.send(action)
        }
    }

}