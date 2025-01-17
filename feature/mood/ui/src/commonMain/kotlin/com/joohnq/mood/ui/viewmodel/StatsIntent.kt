package com.joohnq.mood.ui.viewmodel

import com.joohnq.mood.domain.entity.StatsRecord

sealed class StatsIntent {
    data object GetStatsRecords : StatsIntent()
    data class AddStatsRecord(val statsRecord: StatsRecord) : StatsIntent()
    data class DeleteStatsRecord(val id: Int) : StatsIntent()
}