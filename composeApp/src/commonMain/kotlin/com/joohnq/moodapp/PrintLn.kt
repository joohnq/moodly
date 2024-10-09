package com.joohnq.moodapp

fun printLn(item: Any) {
    println("MoodAppLogCat $item")
}

fun printLn(content: () -> Unit) {
    content()
    println("----------")
}

