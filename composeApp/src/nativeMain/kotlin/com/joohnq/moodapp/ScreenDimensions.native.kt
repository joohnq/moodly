@file:OptIn(ExperimentalForeignApi::class)

package com.joohnq.moodapp

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIApplication
import platform.UIKit.UIScreen

actual class ScreenDimensions: ScreenDimensionsInterface {
    @OptIn(ExperimentalForeignApi::class)
    actual override val statusBarHeight: Int =
        UIApplication.sharedApplication().statusBarFrame.size * 2.54f.toInt()
}
