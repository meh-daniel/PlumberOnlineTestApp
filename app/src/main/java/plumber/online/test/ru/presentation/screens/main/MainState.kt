package plumber.online.test.ru.presentation.screens.main

sealed interface MainState {
    object Idle : MainState
    data class Nothing(val data: String) : MainState
    data class Loaded(val data: String) : MainState
}