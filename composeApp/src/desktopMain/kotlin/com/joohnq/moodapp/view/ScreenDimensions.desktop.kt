package com.joohnq.moodapp.view

actual class ScreenDimensions : ScreenDimensionsInterface {
    actual override val statusBarHeight: Int = 0
    actual override val moodRatePadding: Int = 0
    actual override val osType: OSType = OSType.DESKTOP
}