package com.joohnq.moodapp

interface ScreenDimensionsInterface{
    val statusBarHeight: Int
}

expect class ScreenDimensions: ScreenDimensionsInterface {
    override val statusBarHeight: Int
}
