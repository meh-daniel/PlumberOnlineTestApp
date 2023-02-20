package plumber.online.test.ru.presentation.screens.main

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import plumber.online.test.ru.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel() {

    private val _action: Channel<MainAction> = Channel(Channel.BUFFERED)
    var actionFlow = _action.receiveAsFlow()

    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    var stateFlow = _state.asStateFlow()

}