package com.joohnq.stress_level.ui.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

fun List<StressLevelRecordResource>.getTodayStressLevelRecord(): StressLevelRecordResource? =
    find { record -> record.createdAt.date == getNow().date }