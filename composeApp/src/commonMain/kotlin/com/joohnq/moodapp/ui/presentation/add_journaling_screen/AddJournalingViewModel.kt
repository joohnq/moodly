package com.joohnq.moodapp.ui.presentation.add_journaling_screen

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.util.mappers.toggle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddingJournalingState(
    val mood: Mood? = null,
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val selectedStressStressors: List<Stressor> = mutableListOf(),
    val sliderValue: Float = 0f
)

sealed class AddingJournalingIntent {
    data class UpdateMood(val mood: Mood?) : AddingJournalingIntent()
    data class UpdateTitle(val title: String) : AddingJournalingIntent()
    data class UpdateDescription(val description: String) : AddingJournalingIntent()
    data class UpdateTitleError(val error: String?) : AddingJournalingIntent()
    data class UpdateSliderValue(val sliderValue: Float) : AddingJournalingIntent()
    data class UpdateSelectedStressStressors(val stressor: Stressor) :
        AddingJournalingIntent()

    data object ResetState : AddingJournalingIntent()
}

class AddJournalingViewModel : ViewModel() {
    private val _addingJournalingState = MutableStateFlow(AddingJournalingState())
    val addingJournalingState: StateFlow<AddingJournalingState> =
        _addingJournalingState.asStateFlow()

    fun onAction(intent: AddingJournalingIntent) {
        when (intent) {
            AddingJournalingIntent.ResetState ->
                resetHeathJournal()

            is AddingJournalingIntent.UpdateDescription ->
                updateDescription(intent.description)

            is AddingJournalingIntent.UpdateMood -> updateAddingMood(intent.mood)
            is AddingJournalingIntent.UpdateSelectedStressStressors -> updateSelectedStressStressors(
                intent.stressor
            )

            is AddingJournalingIntent.UpdateSliderValue ->
                updateSliderValue(intent.sliderValue)

            is AddingJournalingIntent.UpdateTitle -> updateTitle(intent.title)
            is AddingJournalingIntent.UpdateTitleError ->
                updateTitleError(intent.error)
        }
    }

    private fun updateAddingMood(mood: Mood?) {
        _addingJournalingState.update { it.copy(mood = mood) }
    }

    private fun updateTitle(title: String) {
        _addingJournalingState.update { it.copy(title = title) }
    }

    private fun updateTitleError(titleError: String?) {
        _addingJournalingState.update { it.copy(titleError = titleError) }
    }

    private fun updateDescription(description: String) {
        _addingJournalingState.update { it.copy(description = description) }
    }

    private fun updateSliderValue(sliderValue: Float) {
        _addingJournalingState.update { it.copy(sliderValue = sliderValue) }
    }

    private fun updateSelectedStressStressors(stressor: Stressor) {
        _addingJournalingState.update {
            it.copy(
                selectedStressStressors = addingJournalingState.value.selectedStressStressors.toggle(
                    stressor
                )
            )
        }
    }

    private fun resetHeathJournal() {
        _addingJournalingState.update { AddingJournalingState() }
    }
}