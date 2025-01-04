package com.joohnq.shared.domain

fun <T> T.toResult(): Result<T> =
    if (this is Throwable) Result.failure(this) else Result.success(this)

fun <T> T?.toResultNull(message: String): Result<T> =
    when (this) {
        null -> Result.failure(Exception(message))
        is Throwable -> Result.failure(this)
        else -> Result.success(this)
    }
