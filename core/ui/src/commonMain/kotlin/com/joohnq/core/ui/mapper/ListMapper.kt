package com.joohnq.core.ui.mapper

fun <T> List<T>.toggle(item: T): List<T> =
    if (contains(item)) minus(item) else plus(item)

fun <T> List<T?>.itemsNotNull(): List<T> = map { it!! }
