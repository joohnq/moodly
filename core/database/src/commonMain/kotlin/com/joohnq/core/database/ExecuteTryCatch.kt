package com.joohnq.core.database

fun executeTryCatch(block: () -> Unit): Boolean =
    try {
        block()
        true
    } catch (e: Exception) {
        false
    }

suspend fun executeTryCatchPrinting(block: suspend () -> Unit): Boolean =
    try {
        block()
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }