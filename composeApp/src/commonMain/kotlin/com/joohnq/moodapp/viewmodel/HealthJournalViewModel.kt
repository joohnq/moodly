package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.data.repository.HealthJournalRepository
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.ui.state.UiState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.util.mappers.toggle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HealthJournalEditing(
    val status: UiState<Boolean> = UiState.Idle,
    val currentHealthJournalRecord: HealthJournalRecord = HealthJournalRecord.init(),
    val editingHealthJournalRecord: HealthJournalRecord = HealthJournalRecord.init(),
    val isEditing: Boolean = false,
    val openDeleteDialog: Boolean = false
)

data class HealthJournalAddingStats(
    val status: UiState<Boolean> = UiState.Idle,
    val mood: Mood? = null,
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val selectedStressStressors: List<Stressor> = mutableListOf(),
    val sliderValue: Float = 0f
)

sealed class HealthJournalIntent {
    data object GetHealthJournals : HealthJournalIntent()
    data class GetHealthJournalById(val id: Int) : HealthJournalIntent()
    data object AddHealthJournal : HealthJournalIntent()
    data class UpdateAddingMood(val mood: Mood?) : HealthJournalIntent()
    data class UpdateAddingTitle(val title: String) : HealthJournalIntent()
    data class UpdateAddingDescription(val description: String) : HealthJournalIntent()
    data class UpdateAddingTitleError(val error: String) : HealthJournalIntent()
    data object ResetAddingHeathJournal : HealthJournalIntent()
    data object ResetDeletingHeathJournal : HealthJournalIntent()
    data object ResetEditingHeathJournal : HealthJournalIntent()
    data class UpdateEditingTitle(val title: String) : HealthJournalIntent()
    data class UpdateEditingDescription(val description: String) : HealthJournalIntent()
    data class UpdateIsEditing(val value: Boolean) : HealthJournalIntent()
    data class UpdateEditingOpenDeleteDialog(val value: Boolean) : HealthJournalIntent()
    data class DeleteHealthJournal(val id: Int) : HealthJournalIntent()
    data object UpdateEditingHealthJournal : HealthJournalIntent()
    data class UpdateAddingSliderValue(val sliderValue: Float) : HealthJournalIntent()
    data class UpdateAddingSelectedStressStressors(val stressor: Stressor) : HealthJournalIntent()
}

data class HealthJournalState(
    val healthJournalRecords: UiState<List<HealthJournalRecord>> = UiState.Idle,
    val adding: HealthJournalAddingStats = HealthJournalAddingStats(),
    val editing: HealthJournalEditing = HealthJournalEditing(),
    val deleting: UiState<Boolean> = UiState.Idle,
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
            is HealthJournalIntent.GetHealthJournalById -> getHealthJournalById(intent.id)
            is HealthJournalIntent.AddHealthJournal -> addHealthJournal()
            is HealthJournalIntent.ResetAddingHeathJournal -> resetAddingHeathJournal()
            is HealthJournalIntent.UpdateAddingDescription -> updateAddingDescription(intent.description)
            is HealthJournalIntent.UpdateAddingMood -> updateAddingMood(intent.mood)
            is HealthJournalIntent.UpdateAddingTitle -> updateAddingTitle(intent.title)
            is HealthJournalIntent.UpdateAddingTitleError -> updateAddingTitleError(intent.error)
            HealthJournalIntent.ResetEditingHeathJournal -> resetEditingHeathJournal()
            is HealthJournalIntent.UpdateEditingTitle -> updateEditingTitle(intent.title)
            is HealthJournalIntent.UpdateEditingDescription -> updateEditingDescription(intent.description)
            is HealthJournalIntent.UpdateIsEditing -> updateIsEditing(intent.value)
            is HealthJournalIntent.UpdateEditingOpenDeleteDialog ->
                updateEditingOpenDeleteDialog(
                    intent.value
                )

            is HealthJournalIntent.DeleteHealthJournal -> deleteHealthJournal(intent.id)
            is HealthJournalIntent.UpdateEditingHealthJournal -> updateEditingHealthJournal()
            HealthJournalIntent.ResetDeletingHeathJournal -> resetDeletingHeathJournal()
            is HealthJournalIntent.UpdateAddingSliderValue -> updateAddingSliderValue(intent.sliderValue)
            is HealthJournalIntent.UpdateAddingSelectedStressStressors -> updateAddingSelectedStressStressors(
                intent.stressor
            )
        }
    }

    private fun addHealthJournal() = viewModelScope.launch(dispatcher) {
        changeAddingStatus(UiState.Loading)

        try {
            val res = healthJournalRepository.addHealthJournal(
                title = healthJournalState.value.adding.title,
                description = healthJournalState.value.adding.description,
                mood = healthJournalState.value.adding.mood!!,
                stressLevel = StressLevel.fromSliderValue(healthJournalState.value.adding.sliderValue),
                stressors = healthJournalState.value.adding.selectedStressStressors
            )
            changeAddingStatus(UiState.Success(res))
        } catch (e: Exception) {
            changeAddingStatus(UiState.Error(e.message.toString()))
        }
    }

    private fun getHealthJournalById(id: Int) =
        viewModelScope.launch(dispatcher) {
            val healthJournalRecord =
                healthJournalState.value.healthJournalRecords.getValue().find { it.id == id }
                    ?: return@launch
            _healthJournalState.update {
                it.copy(
                    editing = it.editing.copy(
                        currentHealthJournalRecord = healthJournalRecord,
                        editingHealthJournalRecord = healthJournalRecord
                    )
                )
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

    private fun updateAddingSliderValue(sliderValue: Float) {
        _healthJournalState.update { it.copy(adding = it.adding.copy(sliderValue = sliderValue)) }
    }

    private fun updateEditingTitle(title: String) {
        _healthJournalState.update {
            it.copy(
                editing = it.editing.copy(
                    editingHealthJournalRecord = it.editing.editingHealthJournalRecord.copy(title = title)
                )
            )
        }
    }

    private fun updateIsEditing(value: Boolean) {
        _healthJournalState.update {
            it.copy(
                editing = it.editing.copy(
                    isEditing = value
                )
            )
        }
    }

    private fun updateEditingDescription(description: String) {
        _healthJournalState.update {
            val editing = it.editing
            it.copy(
                editing = it.editing.copy(
                    editingHealthJournalRecord = editing.editingHealthJournalRecord.copy(
                        description = description
                    ),
                )
            )
        }
    }

    private fun updateEditingOpenDeleteDialog(value: Boolean) {
        _healthJournalState.update {
            it.copy(
                editing = it.editing.copy(
                    openDeleteDialog = value
                )
            )
        }
    }

    private fun updateAddingSelectedStressStressors(stressor: Stressor) {
        val list = _healthJournalState.value.adding.selectedStressStressors
        _healthJournalState.update {
            it.copy(
                adding = it.adding.copy(
                    selectedStressStressors = list.toggle(stressor)
                )
            )
        }
    }

    private fun deleteHealthJournal(id: Int) =
        viewModelScope.launch(dispatcher) {
            _healthJournalState.update { it.copy(deleting = UiState.Loading) }
            try {
                val res = healthJournalRepository.deleteHealthJournal(id)
                _healthJournalState.update { it.copy(deleting = UiState.Success(res)) }
                _healthJournalState.update {
                    it.copy(
                        healthJournalRecords = UiState.Success(
                            it.healthJournalRecords.getValue().filter { item ->
                                item.id != id
                            }
                        )
                    )
                }
                onAction(HealthJournalIntent.GetHealthJournals)
            } catch (e: Exception) {
                _healthJournalState.update { it.copy(deleting = UiState.Error(e.message.toString())) }
            }
        }

    private fun updateEditingHealthJournal() =
        viewModelScope.launch(dispatcher) {
            _healthJournalState.update { it.copy(editing = it.editing.copy(status = UiState.Loading)) }
            try {
                val res =
                    healthJournalRepository.updateHealthJournal(healthJournalState.value.editing.editingHealthJournalRecord)
                _healthJournalState.update {
                    it.copy(
                        editing = it.editing.copy(
                            status = UiState.Success(
                                res
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                _healthJournalState.update {
                    it.copy(
                        editing = it.editing.copy(
                            status = UiState.Error(
                                e.message.toString()
                            )
                        )
                    )
                }
            }
        }

    private fun resetAddingHeathJournal() {
        _healthJournalState.update { it.copy(adding = HealthJournalAddingStats()) }
    }

    private fun resetDeletingHeathJournal() {
        _healthJournalState.update { it.copy(deleting = UiState.Idle) }
    }

    private fun resetEditingHeathJournal() {
        _healthJournalState.update { it.copy(editing = HealthJournalEditing()) }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _healthJournalState.update { it.copy(adding = it.adding.copy(status = status)) }
    }
}