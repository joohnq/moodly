package com.joohnq.moodapp

interface Platform {
    val statusBarHeight: Int
}

expect fun getPlatform(): Platform