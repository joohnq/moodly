package com.joohnq.sleep_quality.domain

import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Companion.fromValue

class SleepQualityRecordConverter {
    fun fromSleepQuality(value: SleepQuality): Int = value.fromValue()
    fun toSleepQuality(value: Int): SleepQuality = SleepQuality.toValue(value)
}