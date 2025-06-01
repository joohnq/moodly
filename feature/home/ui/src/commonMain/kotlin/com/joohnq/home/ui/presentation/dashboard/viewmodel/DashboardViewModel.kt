package com.joohnq.home.ui.presentation.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.entity.UiState
import com.joohnq.freud_score.ui.resource.mapper.toResource
import com.joohnq.mood.domain.use_case.GetMoodsUseCase
import com.joohnq.mood.ui.resource.mapper.calculateStatsFreudScore
import com.joohnq.mood.ui.resource.mapper.toResource
import com.joohnq.self_journal.domain.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.ui.resource.mapper.toResource
import com.joohnq.sleep_quality.domain.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.ui.resource.mapper.toResource
import com.joohnq.stress_level.domain.use_case.GetStressLevelsUseCase
import com.joohnq.stress_level.ui.resource.mapper.toResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getMoodsUseCase: GetMoodsUseCase,
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
    private val getStressLevelsUseCase: GetStressLevelsUseCase,
    private val getSelfJournalsUseCase: GetSelfJournalsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardContract.State())
    val state: StateFlow<DashboardContract.State> = _state.asStateFlow()

    private val _sideEffect = Channel<DashboardContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onIntent(event: DashboardContract.Intent) {
        when (event) {
            DashboardContract.Intent.GetAllItems -> getAll()
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            _state.update { it.copy(status = UiState.Loading) }

            try {
                val mood = getMoodsUseCase().getOrThrow()
                val sleep = getSleepQualitiesUseCase().getOrThrow()
                val stress = getStressLevelsUseCase().getOrThrow()
                val journal = getSelfJournalsUseCase().getOrThrow()
                val freudScore = mood.toResource().calculateStatsFreudScore()

                _state.update {
                    it.copy(
                        moodRecords = mood.toResource(),
                        sleepQualityRecords = sleep.toResource(),
                        stressLevelRecords = stress.toResource(),
                        selfJournalRecords = journal.toResource(),
                        freudScore = freudScore.toResource(),
                        status = UiState.Success(Unit),
                    )
                }
            }catch (e: Exception) {
                _sideEffect.send(
                    DashboardContract.SideEffect.ShowError(e)
                )
            }
        }
    }
}