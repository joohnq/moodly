package com.joohnq.mood.impl.ui.presentation.add_mood

import androidx.lifecycle.ViewModel
import com.joohnq.mood.impl.ui.resource.MoodResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddMoodViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddMoodContract.State())
    val state: StateFlow<AddMoodContract.State> = _state.asStateFlow()

    fun onAction(intent: AddMoodContract.Intent) {
        when (intent) {
            is AddMoodContract.Intent.UpdateAddingMoodRecordMood -> updateAddingMoodRecordMood(
                intent.mood
            )

            is AddMoodContract.Intent.UpdateAddingMoodRecordDescription -> updateAddingMoodRecordDescription(
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