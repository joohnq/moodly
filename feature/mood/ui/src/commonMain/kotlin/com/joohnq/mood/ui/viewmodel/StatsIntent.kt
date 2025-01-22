package com.joohnq.mood.ui.viewmodel

import com.joohnq.mood.domain.entity.StatsRecord

sealed interface StatsIntent {
    data object GetStatsRecords : StatsIntent
    data class AddStatsRecord(val statsRecord: StatsRecord) : StatsIntent
    data class DeleteStatsRecord(val id: Int) : StatsIntent
}