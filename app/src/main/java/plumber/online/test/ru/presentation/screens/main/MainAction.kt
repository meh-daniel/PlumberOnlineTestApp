package plumber.online.test.ru.presentation.screens.main

sealed interface MainAction {
    data class ShowError(val message: String) : MainAction
    object RouteToMap : MainAction
}