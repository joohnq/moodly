package com.joohnq.stress_level.domain.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.stress_level.domain.entity.StressLevelRecord

fun List<StressLevelRecord>.getTodayStressLevelRecord(): StressLevelRecord? =
    find { record -> record.createdAt.date == getNow().date }