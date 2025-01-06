package com.joohnq.mood.ui.presentation.add_stats.viewmodel

import com.joohnq.mood.ui.resource.MoodResource

sealed class AddStatIntent {
    data class UpdateAddingStatsRecordMood(val mood: MoodResource) : AddStatIntent()
    data class UpdateAddingStatsRecordDescription(val description: String) : AddStatIntent()
    data object ResetState : AddStatIntent()
}