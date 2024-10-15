package com.joohnq.moodapp.view.state

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    data object Idle : UiState<Nothing>()
}

fun <T> UiState<T>.fold(
    onLoading: () -> Unit = {},
    onIdle: () -> Unit = {},
    onSuccess: (T) -> Unit = {},
    onError: (String) -> Unit = {}
) {
    when (this) {
        is UiState.Loading -> onLoading()
        is UiState.Success -> onSuccess(this.data)
        is UiState.Error -> onError(this.message)
        is UiState.Idle -> onIdle()
    }
}