package com.joohnq.domain.mapper

import kotlin.math.pow

actual fun Double.toPercentage(decimals: Int): String {
    val rounded = (this * 10.0.pow(decimals.toDouble())).toInt() / 10.0.pow(decimals.toDouble())
    return if (rounded == rounded.toInt().toDouble()) "${rounded.toInt()}%" else "$rounded%"
}