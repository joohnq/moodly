package com.joohnq.moodapp.mappers

import kotlin.math.PI

fun Double.toRadians(): Double = this / 180.0 * PI
fun Double.toDegrees(): Double = this * 180.0 / PI
