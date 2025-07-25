package com.joohnq.home.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.freud_score.impl.presentation.freud_score.FreudScoreContract
import com.joohnq.freud_score.impl.presentation.freud_score.FreudScoreViewModel
import com.joohnq.mood.impl.ui.presentation.mood.MoodContract
import com.joohnq.mood.impl.ui.presentation.mood.MoodViewModel
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalContract
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalViewModel
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityContract
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityViewModel
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelContract
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelViewModel
import com.joohnq.ui.mapper.anyError
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.user.impl.ui.viewmodel.UserContract
import com.joohnq.user.impl.ui.viewmodel.UserViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val userViewModel: UserViewModel,
    private val moodViewModel: MoodViewModel,
    private val freudScoreViewModel: FreudScoreViewModel,
    private val selfJournalViewModel: SelfJournalViewModel,
    private val sleepQualityViewModel: SleepQualityViewModel,
    private val stressLevelViewModel: StressLevelViewModel,
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardContract.State())
    val state: StateFlow<DashboardContract.State> = _state.asStateFlow()

    private val _sideEffect = Channel<DashboardContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(event: DashboardContract.Intent) {
        when (event) {
            DashboardContract.Intent.Get -> {
                moodViewModel.onAction(MoodContract.Intent.GetAll)
                userViewModel.onAction(UserContract.Intent.GetUser)
                stressLevelViewModel.onIntent(StressLevelContract.Intent.GetAll)
                sleepQualityViewModel.onAction(SleepQualityContract.Intent.GetAll)
                selfJournalViewModel.onAction(SelfJournalContract.Intent.GetAll)
            }
        }
    }

    init {
        combine(
            userViewModel.state,
            moodViewModel.state,
            freudScoreViewModel.state,
            selfJournalViewModel.state,
            sleepQualityViewModel.state,
            stressLevelViewModel.state
        ) { states ->
            val state = states[0] as UserContract.State
            val statsState = states[1] as MoodContract.State
            val freudState = states[2] as FreudScoreContract.State
            val healthState = states[3] as SelfJournalContract.State
            val sleepState = states[4] as SleepQualityContract.State
            val stressState = states[5] as StressLevelContract.State

            statsState.records.onSuccess { records ->
                freudScoreViewModel.onIntent(
                    FreudScoreContract.Intent.Get(records)
                )
            }

            listOf(
                statsState.records,
                state.user,
                stressState.records,
                sleepState.records,
                healthState.records,
            ).anyError(
                block = { error ->
                    viewModelScope.launch {
                        _sideEffect.send(DashboardContract.SideEffect.ShowError(error))
                    }
                }
            )

            DashboardContract.State(
                user = state.user,
                moodRecords = statsState.records,
                freudScore = freudState.freudScore,
                selfJournalRecords = healthState.records,
                sleepQualityRecords = sleepState.records,
                stressLevelRecords = stressState.records
            )
        }.onEach { newState ->
            _state.value = newState
        }.launchIn(viewModelScope)
    }
}