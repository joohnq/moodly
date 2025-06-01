package com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.ui.resource.MoodResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddJournalViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddJournalContract.State())
    val state: StateFlow<AddJournalContract.State> =
        _state.asStateFlow()

    fun onIntent(intent: AddJournalContract.Intent) {
        when (intent) {
            AddJournalContract.Intent.ResetState ->
                resetHeathJournal()

            is AddJournalContract.Intent.UpdateDescription ->
                updateDescription(intent.description)

            is AddJournalContract.Intent.UpdateMood -> updateAddingMood(intent.mood)
            is AddJournalContract.Intent.UpdateTitle -> updateTitle(intent.title)
            is AddJournalContract.Intent.UpdateTitleError ->
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
        _state.update { AddJournalContract.State() }
    }
}