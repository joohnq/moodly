package com.joohnq.mood.ui.presentation.add_mood.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.ui.resource.MoodResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddMoodViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddMoodContract.State())
    val state: StateFlow<AddMoodContract.State> = _state.asStateFlow()

    fun onIntent(intent: AddMoodContract.Intent) {
        when (intent) {
            is AddMoodContract.Intent.UpdateMood -> updateAddingMoodRecordMood(
                intent.mood
            )

            is AddMoodContract.Intent.UpdateDescription -> updateAddingMoodRecordDescription(
                intent.description
            )

            AddMoodContract.Intent.ResetState -> _state.update { AddMoodContract.State() }
        }
    }

    private fun updateAddingMoodRecordMood(mood: MoodResource) {
        _state.update { it.copy(record = it.record.copy(mood = mood)) }
    }

    private fun updateAddingMoodRecordDescription(description: String) {
        _state.update { it.copy(record = it.record.copy(description = description)) }
    }
}