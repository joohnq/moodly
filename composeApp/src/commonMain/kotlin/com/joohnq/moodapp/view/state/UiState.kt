package com.joohnq.moodapp.view.state

import androidx.compose.material3.SnackbarHostState
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

        fun onAllSuccess(
            vararg values: UiState<*>,
            onAllSuccess: () -> Unit,
        ) {
            if (values.all { it is Success }) {
                onAllSuccess()
            }
        }

        fun allIsSuccess(vararg values: UiState<*>): Boolean = values.all { it is Success }

        fun onAnyError(
            vararg values: UiState<*>,
            onAnyHasError: (String) -> Unit
        ) {
            values.filterIsInstance<Error>().firstOrNull()?.let { errorState ->
                onAnyHasError(errorState.message)
                return
            }
        }

        fun fold(
            vararg values: UiState<*>,
            onAllSuccess: () -> Unit,
            onAnyHasError: (String) -> Unit
        ) {
            values.filterIsInstance<Error>().firstOrNull()?.let { errorState ->
                onAnyHasError(errorState.message)
                return
            }

            if (values.all { it is Success }) {
                onAllSuccess()
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

        @Composable
        fun <T> UiState<T>.getValue(): T =
            when (this) {
                is Success -> this.data
                else -> throw IllegalStateException("UiState is not Success")
            }

        fun <T> UiState<List<T>>.getValue(): List<T> =
            when (this) {
                is Success -> this.data
                else -> throw IllegalStateException("UiState is not Success")
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

        suspend fun <T> UiState<T>.showErrorOrUnit(
            snackBar: SnackbarHostState
        ) {
            when (this) {
                is Error -> {
                    val error = this.message
                    snackBar.showSnackbar(error)
                }

                else -> {}
            }
        }
    }
}

