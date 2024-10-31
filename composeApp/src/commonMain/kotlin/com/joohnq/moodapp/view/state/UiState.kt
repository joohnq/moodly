package com.joohnq.moodapp.view.state

import androidx.compose.runtime.Composable

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    data object Idle : UiState<Nothing>()

    companion object {
        fun <T> UiState<T>.fold(
            onLoading: () -> Unit = {},
            onIdle: () -> Unit = {},
            onSuccess: (T) -> Unit = {},
            onError: (String) -> Unit = {}
        ) {
            when (this) {
                is Loading -> onLoading()
                is Success -> onSuccess(this.data)
                is Error -> onError(this.message)
                is Idle -> onIdle()
            }
        }

        @Composable
        fun <T> UiState<T>.foldComposable(
            onLoading: @Composable () -> Unit = {},
            onIdle: @Composable () -> Unit = {},
            onSuccess: @Composable (T) -> Unit = {},
            onError: @Composable (String) -> Unit = {}
        ) {
            when (this) {
                is Loading -> onLoading()
                is Success -> onSuccess(this.data)
                is Error -> onError(this.message)
                is Idle -> onIdle()
            }
        }

        @Composable
        fun <T> UiState<T>.onSuccessComposable(
            onSuccess: @Composable (T) -> Unit = {},
        ) {
            when (this) {
                is Success -> onSuccess(this.data)
                else -> {}
            }
        }

        @Composable
        fun <T> UiState<T>.getValueOrNull(): T? =
            when (this) {
                is Success -> this.data
                else -> null
            }

        fun <T> UiState<T>.onSuccess(
            onSuccess: (T) -> Unit = {},
        ) {
            when (this) {
                is Success -> onSuccess(this.data)
                else -> {}
            }
        }

        fun <T> UiState<T>.onLoading(
            onLoading: () -> Unit
        ) {
            when (this) {
                is Loading -> onLoading()
                else -> {}
            }
        }

        fun <T> UiState<T>.onError(
            onError: (String) -> Unit
        ) {
            when (this) {
                is Error -> onError(this.message)
                else -> {}
            }
        }
    }
}

