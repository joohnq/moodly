@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.joohnq.moodapp.view

import android.content.Context

actual class ScreenDimensions(context: Context) : ScreenDimensionsInterface {
    actual override val statusBarHeight: Int = 20
}