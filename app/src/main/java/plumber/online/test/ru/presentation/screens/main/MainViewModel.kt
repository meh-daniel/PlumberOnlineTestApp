package plumber.online.test.ru.presentation.screens.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import plumber.online.test.ru.domain.MapRepository
import plumber.online.test.ru.presentation.base.BaseViewModel
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MapRepository
): BaseViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    var stateFlow = _state.asStateFlow()

    private val _action: Channel<MainAction> = Channel(Channel.BUFFERED)
    var actionFlow = _action.receiveAsFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = repository.get()
                Log.d("xxx123", "${data}")
                setState(MainState.Loaded("Address ${data.lat} ${data.lon}"))
            } catch (e: Throwable) {
                when (e) {
                    is NullPointerException -> setState(MainState.Nothing("Точка не задана"))
                    is ConnectException -> sendAction(MainAction.ShowError("отсутствует интернет"))
                    else -> sendAction(MainAction.ShowError(e.message.toString()))
                }
            }
        }
    }

    fun routeToMapScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            sendAction(MainAction.RouteToMap)
        }
    }

    private fun setState(state: MainState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = state
        }
    }

    fun sendAction(action: MainAction){
        viewModelScope.launch(Dispatchers.Main){
            _action.send(action)
        }
    }

}