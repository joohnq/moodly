package com.joohnq.domain.mapper

import androidx.compose.runtime.Composable
import com.joohnq.domain.entity.UiState

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

fun List<UiState<*>>.anyError(
    block: (Throwable) -> Unit,
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
    onError: @Composable (Throwable) -> Unit = {},
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
        else -> emptyList<T>()
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
        block(this.error)
        this
    }

    else -> this
}

fun <R1, R2> List<UiState<*>>.fold(
    onLoading: () -> Unit = {},
    onError: (Throwable) -> Unit = {},
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
    onError: @Composable (Throwable) -> Unit = {},
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
    onError: @Composable (Throwable) -> Unit = {},
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