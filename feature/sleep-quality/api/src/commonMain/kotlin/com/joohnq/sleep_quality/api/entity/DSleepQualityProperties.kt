package com.joohnq.sleep_quality.api.entity

import com.joohnq.sleep_quality.api.property.SleepQualityProperties

data class DSleepQualityProperties(
    override val id: Int,
    override val level: Int,
) : SleepQualityProperties
