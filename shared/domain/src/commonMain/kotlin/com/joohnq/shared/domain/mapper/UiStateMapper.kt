package com.joohnq.shared.domain.mapper

import androidx.compose.runtime.Composable
import com.joohnq.shared.domain.entity.UiState

fun <T> UiState<T>.fold(
    onLoading: () -> Unit = {},
    onIdle: () -> Unit = {},
    onSuccess: (T) -> Unit = {},
    onError: (String) -> Unit = {},
) =
    when (this) {
        is UiState.Loading -> onLoading()
        is UiState.Success -> onSuccess(this.data)
        is UiState.Error -> onError(this.message)
        is UiState.Idle -> onIdle()
    }

@Composable
fun onFold(
    vararg values: UiState<*>,
    onAllSuccess: @Composable () -> Unit,
    onLoading: @Composable () -> Unit,
) {
    if (values.all { it is UiState.Success }) {
        onAllSuccess()
    } else if (values.any { it is UiState.Loading }) {
        onLoading()
    }
}

fun onAnyError(
    vararg values: UiState<*>,
    onAnyHasError: (String) -> Unit,
) {
    values.filterIsInstance<UiState.Error>().firstOrNull()?.let { errorState ->
        onAnyHasError(errorState.message)
        return
    }
}

fun fold(
    vararg values: UiState<*>,
    onAllSuccess: () -> Unit,
    onAnyHasError: (String) -> Unit,
) {
    values.filterIsInstance<UiState.Error>().firstOrNull()?.let { errorState ->
        onAnyHasError(errorState.message)
        return
    }

    if (values.all { it is UiState.Success }) {
        onAllSuccess()
    }
}

@Composable
fun <T> UiState<T>.foldComposable(
    onLoading: @Composable () -> Unit = {},
    onIdle: @Composable () -> Unit = {},
    onSuccess: @Composable (T) -> Unit = {},
    onError: @Composable (String) -> Unit = {},
) = when (this) {
    is UiState.Loading -> onLoading()
    is UiState.Success -> onSuccess(this.data)
    is UiState.Error -> onError(this.message)
    is UiState.Idle -> onIdle()
}

@Composable
fun <T> UiState<T>.getValue(): T =
    when (this) {
        is UiState.Success -> this.data
        else -> throw IllegalStateException("UiState is not Success")
    }

fun <T> UiState<List<T>>.getValue(): List<T> =
    when (this) {
        is UiState.Success -> this.data
        else -> throw IllegalStateException("UiState is not Success")
    }

fun <T> UiState<List<T>>.getValueOrNull(): List<T>? =
    when (this) {
        is UiState.Success -> this.data
        else -> null
    }

fun <T> UiState<T>.onSuccess(
    onSuccess: (T) -> Unit = {},
) = when (this) {
    is UiState.Success -> onSuccess(this.data)
    else -> {}
}

fun <T> Result<T>.toUiState(): UiState<T> =
    fold(
        onSuccess = { item -> UiState.Success(item) },
        onFailure = { error -> UiState.Error(error.message.toString()) }
    )