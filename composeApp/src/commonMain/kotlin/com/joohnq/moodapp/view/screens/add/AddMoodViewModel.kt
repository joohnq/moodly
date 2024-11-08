package com.joohnq.moodapp.view.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.model.repository.StatsRepository
import com.joohnq.moodapp.view.screens.Screens
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

data class AddMoodState(
    val items: List<StatsRecord> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class AddMoodIntent {
    data class AddItem(val mood: Mood, val description: String, val route: Screens) :
        AddMoodIntent()
}

sealed class AddMoodSideEffect {
    data class ShowToast(val message: String) : AddMoodSideEffect()
    data class NavigateTo(val route: Screens) : AddMoodSideEffect()
}

class AddMoodViewModel(
    private val statsRecordRepository: StatsRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _stressLevelState = MutableStateFlow(AddMoodState())
    val stressLevelState: StateFlow<AddMoodState> = _stressLevelState.asStateFlow()

    private val _sideEffect = Channel<AddMoodSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: AddMoodIntent) {
        when (intent) {
            is AddMoodIntent.AddItem -> addStatsRecord(
                intent.mood,
                intent.description,
                intent.route
            )
        }
    }

    private fun addStatsRecord(mood: Mood, description: String, route: Screens) =
        viewModelScope.launch(dispatcher) {
            val res = statsRecordRepository.addStats(
                StatsRecord.init().copy(
                    mood = mood,
                    description = description
                )
            )
            _sideEffect.send(
                if (res) AddMoodSideEffect.NavigateTo(route) else AddMoodSideEffect.ShowToast("Erro ao inserir item")
            )
        }
}