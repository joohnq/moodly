package com.joohnq.ui.mapper

import com.joohnq.core.ui.entity.UiState

fun <T> T.toResult(): Result<T> =
    if (this is Throwable) Result.failure(this) else Result.success(this)

fun <T> T?.toResultNull(message: String): Result<T> =
    when (this) {
        null -> Result.failure(Exception(message))
        is Throwable -> Result.failure(this)
        else -> Result.success(this)
    }

fun <T> Result<T>.toUiState(): UiState<T> =
    fold(
        onSuccess = { item -> UiState.Success(item) },
        onFailure = { error -> UiState.Error(error) }
    )

fun <T> Result<List<T>>.getOrEmpty(): List<T> = getOrNull() ?: emptyList()

fun <T, R> Result<List<T>>.toResultResource(
    mapper: (T) -> R
): Result<List<R>> {
    if (isFailure) return Result.failure(exceptionOrNull()!!)
    val res = getOrEmpty()
    return Result.success(res.map { mapper(it) })
}