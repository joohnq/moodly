package com.joohnq.moodapp.helper

import kotlinx.datetime.LocalDateTime

object LocalDateHelper {
    fun toValue(src: String): LocalDateTime = LocalDateTime.parse(src)
    fun fromValue(value: LocalDateTime): String = value.toString()
}