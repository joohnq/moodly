package com.joohnq.moodapp.view.screens.add

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddMoodState(
    val addingStatus: UiState<Boolean> = UiState.Idle,
    val statsRecord: StatsRecord = StatsRecord.init().copy(mood = Mood.Depressed)
)

class AddMoodViewModel : ViewModel() {
    private val _addMoodState = MutableStateFlow(AddMoodState())
    val addMoodState: StateFlow<AddMoodState> = _addMoodState.asStateFlow()

    fun updateStatsRecordMood(mood: Mood) {
        _addMoodState.update { it.copy(statsRecord = it.statsRecord.copy(mood = mood)) }
    }

    fun updateStatsRecordDescription(desc: String) {
        _addMoodState.update { it.copy(statsRecord = it.statsRecord.copy(description = desc)) }
    }
}