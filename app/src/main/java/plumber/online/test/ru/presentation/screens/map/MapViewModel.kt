package plumber.online.test.ru.presentation.screens.map

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
import plumber.online.test.ru.presentation.screens.main.MainAction
import plumber.online.test.ru.presentation.screens.main.MainState
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: MapRepository
): BaseViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    var stateFlow = _state.asStateFlow()

    private val _action: Channel<MainAction> = Channel(Channel.BUFFERED)
    var actionFlow = _action.receiveAsFlow()

    init {
        saveCoordinates()
    }

    private fun saveCoordinates() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.get()
            } catch (e: Throwable) {
                when (e) {
                    is NullPointerException -> setState(MainState.Loaded("Точка не задана"))
                    is ConnectException -> setState(MainState.Error("отсутствует интернет"))
                    else -> setState(MainState.Error(e.message.toString()))
                }
            }
        }
    }

    fun goToMap() {
        viewModelScope.launch(Dispatchers.IO) {

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