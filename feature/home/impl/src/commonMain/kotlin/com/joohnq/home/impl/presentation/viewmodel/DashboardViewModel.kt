package com.joohnq.home.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.freud_score.impl.presentation.freud_score.FreudScoreContract
import com.joohnq.freud_score.impl.presentation.freud_score.FreudScoreViewModel
import com.joohnq.mood.impl.ui.presentation.mood.MoodContract
import com.joohnq.mood.impl.ui.presentation.mood.MoodViewModel
import com.joohnq.self_journal.impl.ui.viewmodel.SelfJournalIntent
import com.joohnq.self_journal.impl.ui.viewmodel.SelfJournalState
import com.joohnq.self_journal.impl.ui.viewmodel.SelfJournalViewModel
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityState
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelState
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelViewModel
import com.joohnq.ui.mapper.anyError
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.user.impl.ui.viewmodel.UserIntent
import com.joohnq.user.impl.ui.viewmodel.UserState
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
                userViewModel.onAction(UserIntent.GetUser)
                stressLevelViewModel.onAction(StressLevelIntent.GetAll)
                sleepQualityViewModel.onAction(SleepQualityIntent.GetAll)
                selfJournalViewModel.onAction(SelfJournalIntent.GetAll)
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
            val userState = states[0] as UserState
            val statsState = states[1] as MoodContract.State
            val freudState = states[2] as FreudScoreContract.State
            val healthState = states[3] as SelfJournalState
            val sleepState = states[4] as SleepQualityState
            val stressState = states[5] as StressLevelState

            statsState.records.onSuccess { records ->
                freudScoreViewModel.onAction(
                    FreudScoreContract.Intent.Get(records)
                )
            }

            listOf(
                statsState.records,
                userState.user,
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
                user = userState.user,
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