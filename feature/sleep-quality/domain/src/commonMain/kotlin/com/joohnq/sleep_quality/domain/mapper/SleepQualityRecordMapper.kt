package com.joohnq.sleep_quality.domain.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord

fun List<SleepQualityRecord>.getTodaySleepQualityRecord(): SleepQualityRecord? =
    find { it.createdAt == getNow().date }