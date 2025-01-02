@file:OptIn(ExperimentalForeignApi::class)

package com.joohnq.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication
import platform.UIKit.UIScreen

actual class ScreenDimensions : ScreenDimensionsInterface {
    @OptIn(ExperimentalForeignApi::class)
    actual override val statusBarHeight: Int =
        UIApplication.sharedApplication().statusBarFrame.size * 2.54f.toInt()
    actual override val osType: OSType = OSType.IOS

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable actual override fun deviceHeight(): Dp =
        LocalWindowInfo.current.containerSize.height.pxToPoint().dp
}

fun Int.pxToPoint(): Double = this.toDouble() / UIScreen.mainScreen.scale