package com.joohnq.sleep_quality.domain.mapper

import com.joohnq.domain.entity.Time
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Companion.EXCELLENT
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Companion.FAIR
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Companion.GOOD
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Companion.POOR
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Companion.WORST
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Excellent
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Fair
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Good
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Poor
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Worst
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord

fun Int.toSleepQuality(): SleepQuality = when (this) {
    EXCELLENT.id -> Excellent
    GOOD.id -> Good
    FAIR.id -> Fair
    POOR.id -> Poor
    WORST.id -> Worst
    else -> throw IllegalArgumentException("Unknown sleep quality option: $this")
}

fun SleepQuality?.toInt(): Int = this?.id ?: -1

fun SleepQualityRecord.startSleeping(hour: Int, minute: Int): SleepQualityRecord =
    this.copy(
        startSleeping = Time(hour, minute)
    )

fun SleepQualityRecord.endSleeping(hour: Int, minute: Int): SleepQualityRecord =
    this.copy(
        endSleeping = Time(hour, minute)
    )
