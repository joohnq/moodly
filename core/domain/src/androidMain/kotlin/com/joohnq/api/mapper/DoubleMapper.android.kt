package com.joohnq.api.mapper

actual fun Double.toPercentage(decimals: Int): String {
    require(decimals >= 0) { "Decimals must be non-negative" }

    return when {
        this.isNaN() -> "NaN%"
        this.isInfinite() -> if (this > 0) "+∞%" else "-∞%"
        this == this.toInt().toDouble() -> "${this.toInt()}%"
        else -> "%.${decimals}f%%".format(this)
    }
}
