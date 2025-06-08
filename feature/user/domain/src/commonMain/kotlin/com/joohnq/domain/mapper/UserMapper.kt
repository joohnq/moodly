package com.joohnq.domain.mapper

fun String?.getPrimaryName(): String {
    if (this.isNullOrBlank()) return ""
    val nameSplit = this.split(" ")
    return nameSplit.take(2).joinToString(" ")
}
