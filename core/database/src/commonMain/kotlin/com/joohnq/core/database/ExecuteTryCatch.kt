package com.joohnq.core.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

suspend fun executeTryCatch(block: suspend () -> Unit): Result<Boolean> =
    withContext(Dispatchers.IO) {
        try {
            block()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

suspend fun executeTryCatchPrinting(block: suspend () -> Unit): Result<Boolean> =
    withContext(Dispatchers.IO) {
        try {
            block()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }