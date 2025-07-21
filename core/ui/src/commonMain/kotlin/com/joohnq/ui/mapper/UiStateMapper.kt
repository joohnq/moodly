package com.joohnq.ui.mapper

import androidx.compose.runtime.Composable
import com.joohnq.ui.entity.UiState

fun <T> UiState<T>.fold(
    onLoading: () -> Unit = {},
    onIdle: () -> Unit = {},
    onSuccess: (T) -> Unit = {},
    onError: (String) -> Unit = {},
) = when (this) {
    is UiState.Loading -> onLoading()
    is UiState.Success -> onSuccess(this.data)
    is UiState.Error -> onError(this.error)
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

fun List<UiState<*>>.allSuccess(): Boolean = all { it is UiState.Success }

fun List<UiState<*>>.anyError(
    block: (String) -> Unit,
): List<UiState<*>> {
    filterIsInstance<UiState.Error>().firstOrNull()?.let { errorState ->
        block(errorState.error)
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
    is UiState.Error -> onError(this.error)
    is UiState.Idle -> onIdle()
}

fun <T> UiState<T>.getValueOrNull(): T =
    when (this) {
        is UiState.Success -> this.data
        else -> throw Throwable()
    }

fun <T> UiState<List<T>>.getValueOrEmpty(): List<T> =
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
    block: (String) -> Unit = {},
): UiState<T> = when (this) {
    is UiState.Error -> {
        block(this.error)
        this
    }

    else -> this
}

fun <R1, R2> List<UiState<*>>.fold(
    onLoading: () -> Unit = {},
    onError: (String) -> Unit = {},
    onSuccess: (R1, R2) -> Unit,
) {
    if (any { it is UiState.Error }) {
        val errorState = first { it is UiState.Error } as UiState.Error
        onError(errorState.error)
    } else if (all { it is UiState.Success<*> }) {
        val r1 = this[0] as UiState.Success<R1>
        val r2 = this[1] as UiState.Success<R2>
        onSuccess(r1.data, r2.data)
    } else {
        onLoading()
    }
}

@Composable
fun <R1, R2> List<UiState<*>>.foldComposable(
    onLoading: @Composable () -> Unit = {},
    onError: @Composable (String) -> Unit = {},
    onSuccess: @Composable (R1, R2) -> Unit,
) {
    if (any { it is UiState.Error }) {
        val errorState = first { it is UiState.Error } as UiState.Error
        onError(errorState.error)
    } else if (all { it is UiState.Success<*> }) {
        val r1 = this[0] as UiState.Success<R1>
        val r2 = this[1] as UiState.Success<R2>
        onSuccess(r1.data, r2.data)
    } else {
        onLoading()
    }
}


@Composable
fun <R1, R2, R3, R4, R5> List<UiState<*>>.foldComposable(
    onLoading: @Composable () -> Unit = {},
    onError: @Composable (String) -> Unit = {},
    onSuccess: @Composable (R1, R2, R3, R4, R5) -> Unit,
) {
    if (any { it is UiState.Error }) {
        val errorState = first { it is UiState.Error } as UiState.Error
        onError(errorState.error)
    } else if (all { it is UiState.Success<*> }) {
        val r1 = this[0] as UiState.Success<R1>
        val r2 = this[1] as UiState.Success<R2>
        val r3 = this[2] as UiState.Success<R3>
        val r4 = this[3] as UiState.Success<R4>
        val r5 = this[4] as UiState.Success<R5>
        onSuccess(r1.data, r2.data, r3.data, r4.data, r5.data)
    } else {
        onLoading()
    }
}