package com.joohnq.domain.entity

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val error: Throwable) : UiState<Nothing>()
    data object Idle : UiState<Nothing>()
}
