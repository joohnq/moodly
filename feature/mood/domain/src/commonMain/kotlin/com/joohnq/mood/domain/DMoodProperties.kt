package com.joohnq.mood.domain

data class DMoodProperties
    (override val id: Int, override val healthLevel: Int) : MoodProperties
