package com.joohnq.sleep_quality.domain.entity

import com.joohnq.sleep_quality.domain.property.SleepQualityProperties

data class DSleepQualityProperties(
    override val id: Int, override val level: Int
) : SleepQualityProperties