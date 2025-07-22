package com.joohnq.self_journal.impl.ui.presentation.add_self_journal.viewmodel

import com.joohnq.mood.impl.ui.resource.MoodResource

sealed interface AddSelfJournalIntent {
    data class UpdateMood(val mood: MoodResource?) : AddSelfJournalIntent
    data class UpdateTitle(val title: String) : AddSelfJournalIntent
    data class UpdateDescription(val description: String) : AddSelfJournalIntent
    data class UpdateTitleError(val error: String?) : AddSelfJournalIntent
    data object ResetState : AddSelfJournalIntent
}