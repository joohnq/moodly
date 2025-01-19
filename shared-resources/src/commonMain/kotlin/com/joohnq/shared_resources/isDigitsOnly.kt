package com.joohnq.shared_resources

fun String.isDigitsOnly(): Boolean {
    return all { it.isDigit() }
}