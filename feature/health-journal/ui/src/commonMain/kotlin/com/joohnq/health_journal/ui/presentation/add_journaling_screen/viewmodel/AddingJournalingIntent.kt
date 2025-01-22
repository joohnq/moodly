package com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel

import com.joohnq.mood.ui.resource.MoodResource

sealed interface AddingJournalingIntent {
    data class UpdateMood(val mood: MoodResource?) : AddingJournalingIntent
    data class UpdateTitle(val title: String) : AddingJournalingIntent
    data class UpdateDescription(val description: String) : AddingJournalingIntent
    data class UpdateTitleError(val error: String?) : AddingJournalingIntent
    data object ResetState : AddingJournalingIntent
}