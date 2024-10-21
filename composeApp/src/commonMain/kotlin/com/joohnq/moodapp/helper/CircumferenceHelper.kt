package com.joohnq.moodapp.helper

import kotlin.math.PI

fun Double.toRadians(): Double = this / 180.0 * PI

fun Double.toDegrees(): Double = this * 180.0 / PI

fun Float.toRadians(): Double = this.toDouble().toRadians()

fun Float.toDegrees(): Double = this.toDouble().toDegrees()