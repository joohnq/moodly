package com.joohnq.sleep_quality.ui.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource

fun List<SleepQualityRecordResource>.getTodaySleepQualityRecord(): SleepQualityRecordResource? =
    find { it.createdAt == getNow().date }