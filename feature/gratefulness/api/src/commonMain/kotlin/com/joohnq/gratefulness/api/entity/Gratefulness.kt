package com.joohnq.gratefulness.api.entity

import com.joohnq.api.getNow
import kotlinx.datetime.LocalDateTime

data class Gratefulness(
    val id: Int = 0,
    val iAmGratefulFor: String = "",
    val smallThingIAppreciate: String = "",
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)
