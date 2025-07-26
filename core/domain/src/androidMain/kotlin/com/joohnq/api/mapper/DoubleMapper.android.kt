package com.joohnq.api.mapper

actual fun Double.toPercentage(decimals: Int): String = if (this == this.toInt().toDouble()) "${this.toInt()}%" else "%.2f%%".format(this)
