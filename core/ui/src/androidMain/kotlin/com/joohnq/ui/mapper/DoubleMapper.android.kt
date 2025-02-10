package com.joohnq.ui.mapper

actual fun Double.toPercentage(decimals: Int): String {
    return if (this == this.toInt().toDouble()) "${this.toInt()}%" else "%.2f%%".format(this)
}