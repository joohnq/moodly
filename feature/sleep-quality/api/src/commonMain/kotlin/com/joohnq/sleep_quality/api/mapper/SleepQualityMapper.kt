package com.joohnq.sleep_quality.api.mapper

import com.joohnq.sleep_quality.api.entity.SleepQuality
import com.joohnq.sleep_quality.api.entity.SleepQuality.Companion.EXCELLENT
import com.joohnq.sleep_quality.api.entity.SleepQuality.Companion.FAIR
import com.joohnq.sleep_quality.api.entity.SleepQuality.Companion.GOOD
import com.joohnq.sleep_quality.api.entity.SleepQuality.Companion.POOR
import com.joohnq.sleep_quality.api.entity.SleepQuality.Companion.WORST
import com.joohnq.sleep_quality.api.entity.SleepQuality.Excellent
import com.joohnq.sleep_quality.api.entity.SleepQuality.Fair
import com.joohnq.sleep_quality.api.entity.SleepQuality.Good
import com.joohnq.sleep_quality.api.entity.SleepQuality.Poor
import com.joohnq.sleep_quality.api.entity.SleepQuality.Worst

object SleepQualityMapper {
    fun Long.toSleepQuality(): SleepQuality =
        when (this) {
            EXCELLENT.id -> Excellent
            GOOD.id -> Good
            FAIR.id -> Fair
            POOR.id -> Poor
            WORST.id -> Worst
            else -> throw IllegalArgumentException("Unknown sleep quality option: $this")
        }
}
