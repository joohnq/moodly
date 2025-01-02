package com.joohnq.mood.ui.viewmodel

import com.joohnq.mood.domain.entity.StatsRecord

sealed class StatsIntent {
    data object GetStatsRecords : StatsIntent()
    data class AddStatsRecord(val statsRecord: StatsRecord) : StatsIntent()
    data object ResetAddingStatus : StatsIntent()
}