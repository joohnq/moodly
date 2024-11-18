package com.joohnq.moodapp.entities

data class ValueSetValue<T>(
    val value: T,
    val setValue: (T) -> Unit = {}
)
