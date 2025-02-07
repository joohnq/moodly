package com.joohnq.mood.ui.viewmodel

import com.joohnq.mood.domain.entity.MoodRecord

sealed interface StatsIntent {
    data object GetStatsRecords : StatsIntent
    data class AddStatsRecord(val record: MoodRecord) : StatsIntent
    data class DeleteStatsRecord(val id: Int) : StatsIntent
}