package com.joohnq.self_journal.impl.ui.presentation.add_self_journal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddSelfJournalViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddSelfJournalContract.State())
    val state: StateFlow<AddSelfJournalContract.State> =
        _state.asStateFlow()

    fun onAction(intent: AddSelfJournalContract.Intent) {
        when (intent) {
            AddSelfJournalContract.Intent.ResetState ->
                _state.update { AddSelfJournalContract.State() }

            is AddSelfJournalContract.Intent.UpdateDescription ->
                _state.update { it.copy(description = intent.description) }

            is AddSelfJournalContract.Intent.UpdateMood ->
                _state.update { it.copy(mood = intent.mood) }

            is AddSelfJournalContract.Intent.UpdateTitle ->
                _state.update { it.copy(title = intent.title) }

            is AddSelfJournalContract.Intent.UpdateTitleError ->
                _state.update { it.copy(titleError = intent.error) }
        }
    }
}