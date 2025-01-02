package com.joohnq.mood.ui.presentation.add_stats.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.ui.MoodResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddStatViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddStatState())
    val state: StateFlow<AddStatState> = _state.asStateFlow()

    fun onAction(intent: AddStatIntent) {
        when (intent) {
            is AddStatIntent.UpdateAddingStatsRecordMood -> updateAddingStatsRecordMood(intent.mood)
            is AddStatIntent.UpdateAddingStatsRecordDescription -> updateAddingStatsRecordDescription(
                intent.description
            )

            AddStatIntent.ResetState -> resetState()
        }
    }

    private fun updateAddingStatsRecordMood(mood: MoodResource) {
        _state.update { it.copy(mood = mood) }
    }

    private fun updateAddingStatsRecordDescription(description: String) {
        _state.update { it.copy(description = description) }
    }

    private fun resetState() {
        _state.update { AddStatState() }
    }
}