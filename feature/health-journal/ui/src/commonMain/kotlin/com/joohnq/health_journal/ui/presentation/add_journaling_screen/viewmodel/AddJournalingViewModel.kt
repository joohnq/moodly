package com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.ui.resource.MoodResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddJournalingViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddingJournalingState())
    val state: StateFlow<AddingJournalingState> =
        _state.asStateFlow()

    fun onAction(intent: AddingJournalingIntent) {
        when (intent) {
            AddingJournalingIntent.ResetState ->
                resetHeathJournal()

            is AddingJournalingIntent.UpdateDescription ->
                updateDescription(intent.description)

            is AddingJournalingIntent.UpdateMood -> updateAddingMood(intent.mood)
            is AddingJournalingIntent.UpdateTitle -> updateTitle(intent.title)
            is AddingJournalingIntent.UpdateTitleError ->
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
        _state.update { AddingJournalingState() }
    }
}