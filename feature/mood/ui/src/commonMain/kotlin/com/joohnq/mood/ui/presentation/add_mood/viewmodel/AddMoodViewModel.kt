package com.joohnq.mood.ui.presentation.add_mood.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.ui.resource.MoodResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddMoodViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddMoodState())
    val state: StateFlow<AddMoodState> = _state.asStateFlow()

    fun onAction(intent: AddMoodIntent) {
        when (intent) {
            is AddMoodIntent.UpdateAddingStatsRecordMood -> updateAddingStatsRecordMood(intent.mood)
            is AddMoodIntent.UpdateAddingStatsRecordDescription -> updateAddingStatsRecordDescription(
                intent.description
            )

            AddMoodIntent.ResetState -> _state.update { AddMoodState() }
        }
    }

    private fun updateAddingStatsRecordMood(mood: MoodResource) {
        _state.update { it.copy(record = it.record.copy(mood = mood)) }
    }

    private fun updateAddingStatsRecordDescription(description: String) {
        _state.update { it.copy(record = it.record.copy(description = description)) }
    }
}