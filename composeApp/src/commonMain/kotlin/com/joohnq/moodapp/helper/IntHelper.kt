package com.joohnq.moodapp.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Int.toDp(): Dp =
    with(LocalDensity.current) { this@toDp.toDp() }