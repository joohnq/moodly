package com.joohnq.domain.mapper

import com.joohnq.domain.entity.Time

fun String.isDigitsOnly(): Boolean {
    return all { it.isDigit() }
}

fun String.toTime(): Time {
    val parts = split(":")
    return Time(parts[0].toInt(), parts[1].toInt())
}

fun String.capitalize(): String = replaceFirstChar { c -> c.uppercase() }

fun String.toWordCount(): Int = split(" ").size
