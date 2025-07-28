package com.joohnq.api.mapper

import kotlin.math.PI
import kotlin.math.pow

object DoubleMapper {
    fun Double.toRadians(): Double = this / 180.0 * PI

    fun Double.toDegrees(): Double = this * 180.0 / PI

    fun List<Double>.organizeMoodRange(): List<Double> = if (size < 8) listOf(0.0) + this + 0.0 else this

    fun Double.toPercentage(decimals: Int = 1): String {
        val rounded = (this * 10.0.pow(decimals.toDouble())).toInt() / 10.0.pow(decimals.toDouble())
        return if (rounded == rounded.toInt().toDouble()) "${rounded.toInt()}%" else "$rounded%"
    }
}
