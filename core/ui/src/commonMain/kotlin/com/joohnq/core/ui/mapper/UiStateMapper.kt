package com.joohnq.core.ui.mapper

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState

fun <T> UiState<T>.fold(
    onLoading: () -> Unit = {},
    onIdle: () -> Unit = {},
    onSuccess: (T) -> Unit = {},
    onError: (String) -> Unit = {},
) = when (this) {
    is UiState.Loading -> onLoading()
    is UiState.Success -> onSuccess(this.data)
    is UiState.Error -> onError(this.exception.message.toString())
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
        onAnyHasError(errorState.exception.message.toString())
        return
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
    is UiState.Error -> onError(this.exception.message.toString())
    is UiState.Idle -> onIdle()
}

@Composable
fun <T> UiState<T>.getValueOrNull(): T? =
    when (this) {
        is UiState.Success -> this.data
        else -> null
    }

fun <T> UiState<List<T>>.getValueOrNull(): List<T> =
    when (this) {
        is UiState.Success -> this.data
        else -> emptyList()
    }

inline fun <T> UiState<T>.onSuccess(
    block: (T) -> Unit = {},
): UiState<T> = when (this) {
    is UiState.Success -> {
        block(this.data)
        this
    }

    else -> this
}

inline fun <T> UiState<T>.onFailure(
    block: (Throwable) -> Unit = {},
): UiState<T> = when (this) {
    is UiState.Error -> {
        block(this.exception)
        this
    }

    else -> this
}

fun <T> Result<T>.toUiState(): UiState<T> =
    fold(
        onSuccess = { item -> UiState.Success(item) },
        onFailure = { error -> UiState.Error(error) }
    )