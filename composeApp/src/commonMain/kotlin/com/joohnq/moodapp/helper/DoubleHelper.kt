package com.joohnq.moodapp.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

fun Double.toPositive() = this * -1

@Composable
fun Double.toDp(): Dp =
    this@toDp.toInt().toDp()