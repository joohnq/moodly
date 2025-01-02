package com.joohnq.mood.ui.presentation.add_stats.viewmodel

import com.joohnq.mood.domain.entity.Mood

sealed class AddStatIntent {
    data class UpdateAddingStatsRecordMood(val mood: Mood) : AddStatIntent()
    data class UpdateAddingStatsRecordDescription(val description: String) : AddStatIntent()
    data object ResetState : AddStatIntent()
}