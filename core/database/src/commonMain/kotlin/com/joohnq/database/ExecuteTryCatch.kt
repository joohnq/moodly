package com.joohnq.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

suspend fun <T> executeTryCatchResult(block: suspend () -> T): Result<T> =
    withContext(Dispatchers.IO) {
        try {
            val res = block()
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

suspend fun <T> executeTryCatch(block: suspend () -> T): T =
    withContext(Dispatchers.IO) {
        block()
    }
