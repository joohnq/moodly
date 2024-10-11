package com.joohnq.moodapp.helper

fun Float.toPositive() = if(this < 0) this * -1 else this