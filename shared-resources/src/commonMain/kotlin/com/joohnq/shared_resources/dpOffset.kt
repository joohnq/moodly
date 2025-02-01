package com.joohnq.shared_resources

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.dpOffset(x: Dp = 0.dp, y: Dp = 0.dp): Modifier =
    offset {
        IntOffset(x = x.toPx().toInt(), y = y.toPx().toInt())
    }