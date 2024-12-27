package com.joohnq.mood

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

actual class ScreenDimensions : ScreenDimensionsInterface {
    actual override val statusBarHeight: Int = 0
    actual override val osType: OSType = OSType.DESKTOP

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    actual override fun deviceHeight(): Dp = LocalWindowInfo.current.containerSize.height.dp
}