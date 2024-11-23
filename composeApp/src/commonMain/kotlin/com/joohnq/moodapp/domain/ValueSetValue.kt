package com.joohnq.moodapp.domain

data class ValueSetValue<T>(
    val value: T,
    val setValue: (T) -> Unit = {}
)

data class ValueSetValueList<T>(
    val value: List<T>,
    val setValue: (T) -> Unit = {}
)

data class ValueSetValueTyped<T, K>(
    val value: T,
    val setValue: K
)
