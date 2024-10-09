package com.joohnq.moodapp

import android.content.Context
import android.util.DisplayMetrics

actual class ScreenDimensions(context: Context): ScreenDimensionsInterface {
    private val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    actual override val statusBarHeight: Int = 0
}