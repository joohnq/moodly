package com.joohnq.database.converters

object BooleanConverter {
    fun toValue(bool: Long): Boolean = bool.toInt() == 1

    fun fromValue(value: Boolean): Long = if (value) 1 else 0
}
