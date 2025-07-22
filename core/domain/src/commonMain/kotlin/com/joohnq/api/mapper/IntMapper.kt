package com.joohnq.api.mapper

fun Int.toPaddedString(): String = if (this < 10) "0$this" else "$this"
