package com.joohnq.moodapp

import android.os.Build

class AndroidPlatform : Platform {
    override val statusBarHeight: Int = 30
}

actual fun getPlatform(): Platform = AndroidPlatform()