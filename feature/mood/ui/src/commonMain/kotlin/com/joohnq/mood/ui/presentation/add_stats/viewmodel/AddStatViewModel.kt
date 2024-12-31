package com.joohnq.mood.ui.presentation.add_stats.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.domain.entity.Mood
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AddStatViewModel : ViewModel() {
    private val _addStatState = MutableStateFlow(AddStatState())
    val addStatState: StateFlow<AddStatState> = _addStatState.asStateFlow()

    fun onAction(intent: AddStatIntent) {
        when (intent) {
            is AddStatIntent.UpdateAddingStatsRecordMood -> updateAddingStatsRecordMood(intent.mood)
            is AddStatIntent.UpdateAddingStatsRecordDescription -> updateAddingStatsRecordDescription(
                intent.description
            )

            AddStatIntent.ResetState -> resetState()
        }
    }

    private fun updateAddingStatsRecordMood(mood: Mood) {
        _addStatState.update { it.copy(mood = mood) }
    }

    private fun updateAddingStatsRecordDescription(description: String) {
        _addStatState.update { it.copy(description = description) }
    }

    private fun resetState() {
        _addStatState.update { AddStatState() }
    }
}