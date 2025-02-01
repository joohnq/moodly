package com.joohnq.mood.domain.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.mood.domain.entity.StatsRecord

fun List<StatsRecord>.getTodayStatRecord(): StatsRecord? =
    find { it.createdAt.date == getNow().date }