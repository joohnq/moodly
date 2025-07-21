package com.joohnq.mood.impl.ui.presentation.add_mood.viewmodel

import com.joohnq.mood.ui.resource.MoodResource

sealed interface AddMoodIntent {
    data class UpdateAddingMoodRecordMood(val mood: MoodResource) : AddMoodIntent
    data class UpdateAddingMoodRecordDescription(val description: String) : AddMoodIntent
    data object ResetState : AddMoodIntent
}