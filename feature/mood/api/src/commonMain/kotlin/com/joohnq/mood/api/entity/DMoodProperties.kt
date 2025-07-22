package com.joohnq.mood.api.entity

import com.joohnq.mood.api.property.MoodProperties

data class DMoodProperties
    (override val id: Int, override val healthLevel: Int) : MoodProperties
