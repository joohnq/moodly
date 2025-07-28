package com.joohnq.api.mapper

object IntMapper {
    fun Int.toPaddedString(): String = if (this < 10) "0$this" else "$this"
}
