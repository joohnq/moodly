@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.joohnq.moodapp.view

interface ScreenDimensionsInterface{
    val statusBarHeight: Int
    val moodRatePadding: Int
}

expect class ScreenDimensions: ScreenDimensionsInterface {
    override val statusBarHeight: Int
    override val moodRatePadding: Int
}
