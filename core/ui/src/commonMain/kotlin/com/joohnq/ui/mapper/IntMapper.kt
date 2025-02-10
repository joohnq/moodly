package com.joohnq.ui.mapper

fun Int.toPaddedString(): String = if (this < 10) "0$this" else "$this"
