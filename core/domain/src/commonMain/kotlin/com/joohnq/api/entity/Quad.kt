package com.joohnq.api.entity

data class Quad<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
)
