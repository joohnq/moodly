@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.joohnq.moodapp

import android.content.Context
import android.util.DisplayMetrics

actual class ScreenDimensions(context: Context): ScreenDimensionsInterface {
    private val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    actual override val statusBarHeight: Int = 20
}