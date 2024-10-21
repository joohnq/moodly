package com.joohnq.moodapp.viewmodel

suspend fun executeWithBoolean(block: suspend () -> Unit) = try {
    block()
    true
} catch (e: Exception) {
    e.printStackTrace()
    false
}