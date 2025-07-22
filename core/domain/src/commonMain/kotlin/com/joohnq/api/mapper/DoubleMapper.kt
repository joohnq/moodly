package com.joohnq.api.mapper

import kotlin.math.PI

fun Double.toRadians(): Double = this / 180.0 * PI
fun Double.toDegrees(): Double = this * 180.0 / PI

expect fun Double.toPercentage(decimals: Int = 2): String

fun List<Double>.organizeMoodRange(): List<Double> =
    if (size < 8) listOf(0.0) + this + 0.0 else this
