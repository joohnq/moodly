package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.HealthJournalRecord
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.model.repository.HealthJournalRepository
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HealthJournalAddingStats(
    val status: UiState<Boolean> = UiState.Idle,
    val mood: Mood? = null,
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
)

sealed class HealthJournalIntent {
    data object GetHealthJournals : HealthJournalIntent()
    data object AddHealthJournal : HealthJournalIntent()
    data class UpdateAddingMood(val mood: Mood?) : HealthJournalIntent()
    data class UpdateAddingTitle(val title: String) : HealthJournalIntent()
    data class UpdateAddingDescription(val description: String) : HealthJournalIntent()
    data class UpdateAddingTitleError(val error: String) : HealthJournalIntent()
    data object ResetAddingHeathJournal : HealthJournalIntent()
}

data class HealthJournalState(
    val healthJournalRecords: UiState<List<HealthJournalRecord>> = UiState.Idle,
    val adding: HealthJournalAddingStats = HealthJournalAddingStats(),
)

class HealthJournalViewModel(
    private val healthJournalRepository: HealthJournalRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _healthJournalState = MutableStateFlow(HealthJournalState())
    val healthJournalState: StateFlow<HealthJournalState> = _healthJournalState.asStateFlow()

    fun onAction(intent: HealthJournalIntent) {
        when (intent) {
            is HealthJournalIntent.GetHealthJournals -> getHealthJournals()
            is HealthJournalIntent.AddHealthJournal -> {}
            is HealthJournalIntent.ResetAddingHeathJournal -> resetAddingHeathJournal()
            is HealthJournalIntent.UpdateAddingDescription -> updateAddingDescription(intent.description)
            is HealthJournalIntent.UpdateAddingMood -> updateAddingMood(intent.mood)
            is HealthJournalIntent.UpdateAddingTitle -> updateAddingTitle(intent.title)
            is HealthJournalIntent.UpdateAddingTitleError -> updateAddingTitleError(intent.error)
        }
    }

    private fun getHealthJournals() =
        viewModelScope.launch(dispatcher) {
            _healthJournalState.update { it.copy(healthJournalRecords = UiState.Loading) }

            try {
                val res = healthJournalRepository.getHealthJournals()
                _healthJournalState.update { it.copy(healthJournalRecords = UiState.Success(res)) }
            } catch (e: Exception) {
                _healthJournalState.update { it.copy(healthJournalRecords = UiState.Error(e.message.toString())) }
            }
        }

    private fun updateAddingMood(mood: Mood?) {
        _healthJournalState.update { it.copy(adding = it.adding.copy(mood = mood)) }
    }

    private fun updateAddingTitle(title: String) {
        _healthJournalState.update { it.copy(adding = it.adding.copy(title = title)) }
    }

    private fun updateAddingTitleError(titleError: String) {
        _healthJournalState.update { it.copy(adding = it.adding.copy(titleError = titleError)) }
    }

    private fun updateAddingDescription(description: String) {
        _healthJournalState.update { it.copy(adding = it.adding.copy(description = description)) }
    }

    private fun resetAddingHeathJournal() {
        _healthJournalState.update { it.copy(adding = HealthJournalAddingStats()) }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _healthJournalState.update { it.copy(adding = it.adding.copy(status = status)) }
    }
}