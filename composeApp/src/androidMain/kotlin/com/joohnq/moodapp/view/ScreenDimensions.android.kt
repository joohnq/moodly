@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.joohnq.moodapp.view

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

actual class ScreenDimensions(context: Context) : ScreenDimensionsInterface {
    actual override val statusBarHeight: Int = 20
    actual override val moodRatePadding: Int = -20
    actual override val osType: OSType = OSType.ANDROID
    @Composable actual override fun deviceHeight(): Dp =
        LocalConfiguration.current.screenHeightDp.dp
}