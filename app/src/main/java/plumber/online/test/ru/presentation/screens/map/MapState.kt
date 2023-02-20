package plumber.online.test.ru.presentation.screens.map

sealed interface MapState {
    object Idle : MapState
    data class Loaded(val data: String) : MapState
    data class Error(val reason: String) : MapState
}