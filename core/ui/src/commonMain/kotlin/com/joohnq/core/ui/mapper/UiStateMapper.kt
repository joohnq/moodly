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
fun List<UiState<*>>.foldComposable(
    onLoading: @Composable () -> Unit,
    block: @Composable () -> Unit,
) {
    if (all { it is UiState.Success }) {
        block()
    } else if (any { it is UiState.Loading }) {
        onLoading()
    }
}

fun List<UiState<*>>.allSuccess(
    block: () -> Unit,
): List<UiState<*>> {
    if (all { it is UiState.Success }) {
        block()
    }
    return this
}

fun List<UiState<*>>.anyError(
    block: (String) -> Unit,
): List<UiState<*>> {
    filterIsInstance<UiState.Error>().firstOrNull()?.let { errorState ->
        block(errorState.exception.message.toString())
        return@let
    }
    return this

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

fun <T> UiState<T>.getValueOrNull(): T =
    when (this) {
        is UiState.Success -> this.data
        else -> throw Throwable()
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