package plumber.online.test.ru.presentation.screens.main

sealed interface MainState {
    object Loading : MainState
    data class Data(val data: String) : MainState
    data class InvalidInput(val reason: String) : MainState
}