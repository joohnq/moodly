package com.joohnq.moodapp.utils

fun printLn(item: Any) {
    println("MoodAppLogCat $item")
}

fun printLn(content: () -> Unit) {
    content()
    println("----------")
}

