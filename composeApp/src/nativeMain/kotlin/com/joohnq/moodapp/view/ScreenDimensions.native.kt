@file:OptIn(ExperimentalForeignApi::class)

package com.joohnq.moodapp.view

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication

actual class ScreenDimensions: ScreenDimensionsInterface {
    @OptIn(ExperimentalForeignApi::class)
    actual override val statusBarHeight: Int =
        UIApplication.sharedApplication().statusBarFrame.size * 2.54f.toInt()
    actual override val moodRatePadding: Int = 40
    actual override val osType: OSType = OSType.IOS
}
