@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package com.joohnq.moodapp

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication

class IOSPlatform : Platform {
    @OptIn(ExperimentalForeignApi::class)
    override val statusBarHeight: Int =
        UIApplication.sharedApplication().statusBarFrame.size * 2.54f.toInt()
}

actual fun getPlatform(): Platform = IOSPlatform()