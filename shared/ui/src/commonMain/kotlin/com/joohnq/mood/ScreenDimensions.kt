package com.joohnq.mood

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import org.koin.core.annotation.Single

interface ScreenDimensionsInterface {
    val statusBarHeight: Int
    val osType: OSType
    @Composable fun deviceHeight(): Dp
}

enum class OSType {
    IOS, ANDROID
}

@Single
expect class ScreenDimensions : ScreenDimensionsInterface {
    override val statusBarHeight: Int
    override val osType: OSType
    @Composable override fun deviceHeight(): Dp
}
