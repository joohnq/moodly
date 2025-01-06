package com.joohnq.shared_resources.util.mappers

fun <T> List<T>.toggle(item: T) =
    if (contains(item)) minus(item) else plus(item)