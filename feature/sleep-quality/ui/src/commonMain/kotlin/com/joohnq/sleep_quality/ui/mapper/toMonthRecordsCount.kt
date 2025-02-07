package com.joohnq.sleep_quality.ui.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource

fun List<SleepQualityRecordResource>.toMonthRecordsCount(): Int =
    filter { it.createdAt.month == getNow().date.month }.size
