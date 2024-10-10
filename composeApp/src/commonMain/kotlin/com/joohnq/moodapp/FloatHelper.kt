package com.joohnq.moodapp

fun Float.toPositive() = if(this < 0) this * -1 else this