package com.joohnq.mood.ui.presentation.add_mood.viewmodel

import com.joohnq.mood.ui.resource.MoodResource

sealed interface AddMoodIntent {
    data class UpdateAddingStatsRecordMood(val mood: MoodResource) : AddMoodIntent
    data class UpdateAddingStatsRecordDescription(val description: String) : AddMoodIntent
    data object ResetState : AddMoodIntent
}