package com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel

import com.joohnq.mood.ui.resource.MoodResource

sealed class AddingJournalingViewModelIntent {
    data class UpdateMood(val mood: MoodResource?) : AddingJournalingViewModelIntent()
    data class UpdateTitle(val title: String) : AddingJournalingViewModelIntent()
    data class UpdateDescription(val description: String) : AddingJournalingViewModelIntent()
    data class UpdateTitleError(val error: String?) : AddingJournalingViewModelIntent()
    data object ResetState : AddingJournalingViewModelIntent()
}