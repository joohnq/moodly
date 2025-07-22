package com.joohnq.mood.api.property

interface MoodProperties {
    fun toResource()

    val id: Int
    val healthLevel: Int
}
