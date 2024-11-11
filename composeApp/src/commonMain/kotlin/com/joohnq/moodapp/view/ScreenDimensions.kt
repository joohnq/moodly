@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.joohnq.moodapp.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

interface ScreenDimensionsInterface {
    val statusBarHeight: Int
    val moodRatePadding: Int
    val osType: OSType
    @Composable fun deviceHeight(): Dp
}

enum class OSType {
    IOS, ANDROID, DESKTOP
}

expect class ScreenDimensions : ScreenDimensionsInterface {
    override val statusBarHeight: Int
    override val moodRatePadding: Int
    override val osType: OSType
    @Composable override fun deviceHeight(): Dp
}
