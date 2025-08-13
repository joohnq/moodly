package com.joohnq.gratefulness.api.entity

data class GratefulnessInsight(
    val items: List<String>,
    val principal: String? = null,
    val principalCount: Int = 0,
)
