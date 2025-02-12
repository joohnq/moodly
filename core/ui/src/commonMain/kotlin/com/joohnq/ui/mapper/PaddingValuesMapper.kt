package com.joohnq.ui.mapper

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PaddingValues.copy(
    top: Dp? = null,
    start: Dp? = null,
    end: Dp? = null,
    bottom: Dp? = null
): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        top = top ?: this.calculateTopPadding(),
        start = start ?: this.calculateStartPadding(layoutDirection),
        end = end ?: this.calculateEndPadding(layoutDirection),
        bottom = bottom ?: this.calculateBottomPadding()
    )
}

@Composable
fun PaddingValues.plus(
    top: Dp = 0.dp,
    start: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp
): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        top = this.calculateTopPadding() + top,
        start = this.calculateStartPadding(layoutDirection) + start,
        end = this.calculateEndPadding(layoutDirection) + end,
        bottom = this.calculateBottomPadding() + bottom
    )
}