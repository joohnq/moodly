package com.joohnq.moodapp.view.entities

import kotlinx.datetime.LocalDate

fun LocalDate.fromValue(): String = this.toString()
fun String.toLocalDateValue(): LocalDate = LocalDate.parse(this)