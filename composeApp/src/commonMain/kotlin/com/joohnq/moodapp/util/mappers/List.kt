package com.joohnq.moodapp.util.mappers

fun <T> List<T>.toggle(item: T) =
    if (contains(item)) minus(item) else plus(item)