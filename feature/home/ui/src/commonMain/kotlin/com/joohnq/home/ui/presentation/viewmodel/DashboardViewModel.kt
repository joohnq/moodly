package com.joohnq.home.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.mapper.anyError
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.freud_score.ui.viewmodel.FreudScoreIntent
import com.joohnq.freud_score.ui.viewmodel.FreudScoreState
import com.joohnq.freud_score.ui.viewmodel.FreudScoreViewModel
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalState
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsState
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityState
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelState
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.viewmodel.user.UserIntent
import com.joohnq.user.ui.viewmodel.user.UserState
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val userViewModel: UserViewModel,
    private val statsViewModel: StatsViewModel,
    private val freudScoreViewModel: FreudScoreViewModel,
    private val healthJournalViewModel: HealthJournalViewModel,
    private val sleepQualityViewModel: SleepQualityViewModel,
    private val stressLevelViewModel: StressLevelViewModel
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    private val _sideEffect = Channel<DashboardSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(event: DashboardIntent) {
        when (event) {
            DashboardIntent.GetData -> {
                statsViewModel.onAction(StatsIntent.GetStatsRecords)
                userViewModel.onAction(UserIntent.GetUser)
                stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)
                sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
                healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
            }
        }
    }

    init {
        combine(
            userViewModel.state,
            statsViewModel.state,
            freudScoreViewModel.state,
            healthJournalViewModel.state,
            sleepQualityViewModel.state,
            stressLevelViewModel.state
        ) { states ->
            val userState = states[0] as UserState
            val statsState = states[1] as StatsState
            val freudState = states[2] as FreudScoreState
            val healthState = states[3] as HealthJournalState
            val sleepState = states[4] as SleepQualityState
            val stressState = states[5] as StressLevelState

            statsState.statsRecords.onSuccess { records ->
                freudScoreViewModel.onAction(
                    FreudScoreIntent.GetFreudScore(records)
                )
            }

            listOf(
                statsState.statsRecords,
                userState.user,
                stressState.stressLevelRecords,
                sleepState.sleepQualityRecords,
                healthState.healthJournalRecords,
            ).anyError(
                block = { error ->
                    viewModelScope.launch {
                        _sideEffect.send(DashboardSideEffect.ShowError(error.message.toString()))
                    }
                }
            )

            DashboardState(
                user = userState.user,
                statsRecords = statsState.statsRecords,
                freudScore = freudState.freudScore,
                healthJournalRecords = healthState.healthJournalRecords,
                sleepQualityRecords = sleepState.sleepQualityRecords,
                stressLevelRecords = stressState.stressLevelRecords
            )
        }.onEach { newState ->
            _state.value = newState
        }.launchIn(viewModelScope)
    }
}