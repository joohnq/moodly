package com.joohnq.moodapp.utils

fun Log(item: Any) {
    println("MoodAppLogCat $item")
}

fun Log(content: () -> Unit) {
    content()
    println("----------")
}

