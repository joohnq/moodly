package com.joohnq.self_journal.impl.ui.presentation.add_self_journal.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.ui.resource.MoodResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddJournalingViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddSelfJournalState())
    val state: StateFlow<AddSelfJournalState> =
        _state.asStateFlow()

    fun onAction(intent: AddSelfJournalIntent) {
        when (intent) {
            AddSelfJournalIntent.ResetState ->
                resetHeathJournal()

            is AddSelfJournalIntent.UpdateDescription ->
                updateDescription(intent.description)

            is AddSelfJournalIntent.UpdateMood -> updateAddingMood(intent.mood)
            is AddSelfJournalIntent.UpdateTitle -> updateTitle(intent.title)
            is AddSelfJournalIntent.UpdateTitleError ->
                updateTitleError(intent.error)
        }
    }

    private fun updateAddingMood(mood: MoodResource?) {
        _state.update { it.copy(mood = mood) }
    }

    private fun updateTitle(title: String) {
        _state.update { it.copy(title = title) }
    }

    private fun updateTitleError(titleError: String?) {
        _state.update { it.copy(titleError = titleError) }
    }

    private fun updateDescription(description: String) {
        _state.update { it.copy(description = description) }
    }

    private fun resetHeathJournal() {
        _state.update { AddSelfJournalState() }
    }
}