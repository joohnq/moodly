package com.joohnq.mood.domain.entity

import com.joohnq.mood.domain.MoodProperties

data class DMoodProperties
    (override val id: Int, override val healthLevel: Int) : MoodProperties
