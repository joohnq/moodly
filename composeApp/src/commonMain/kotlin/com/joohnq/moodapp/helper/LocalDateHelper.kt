package com.joohnq.moodapp.helper

import kotlinx.datetime.LocalDate

object LocalDateHelper {
    fun toValue(src: String): LocalDate = LocalDate.parse(src)
    fun fromValue(value: LocalDate): String = value.toString()
}