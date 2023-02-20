package plumber.online.test.ru.presentation.screens.map

sealed interface MapAction {
    data class ShowError(val message: String) : MapAction
    object RouteToMainScreen : MapAction
}