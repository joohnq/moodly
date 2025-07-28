package com.joohnq.ui.mapper

import com.joohnq.ui.entity.UiState

object ResultMapper {
    fun <T> Result<T>.toUiState(): UiState<T> =
        fold(
            onSuccess = { item -> UiState.Success(item) },
            onFailure = { error -> UiState.Error(error.message.toString()) }
        )

    fun <T> Result<List<T>>.getOrEmpty(): List<T> = getOrNull() ?: emptyList()

    fun <T, R> Result<List<T>>.toResultResource(mapper: (T) -> R): Result<List<R>> {
        if (isFailure) return Result.failure(exceptionOrNull()!!)
        val res = getOrEmpty()
        return Result.success(res.map { mapper(it) })
    }
}
