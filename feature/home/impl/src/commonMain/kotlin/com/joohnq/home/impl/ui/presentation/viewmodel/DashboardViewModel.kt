package com.joohnq.home.impl.ui.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreContract
import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreViewModel
import com.joohnq.mood.impl.ui.presentation.mood.MoodContract
import com.joohnq.mood.impl.ui.presentation.mood.MoodViewModel
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalContract
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalViewModel
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityContract
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityViewModel
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelContract
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelViewModel
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.mapper.anyError
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.user.impl.ui.viewmodel.UserContract
import com.joohnq.user.impl.ui.viewmodel.UserViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DashboardViewModel(
    initialState: DashboardContract.State = DashboardContract.State(),
    private val userViewModel: UserViewModel,
    private val moodViewModel: MoodViewModel,
    private val freudScoreViewModel: FreudScoreViewModel,
    private val selfJournalViewModel: SelfJournalViewModel,
    private val sleepQualityViewModel: SleepQualityViewModel,
    private val stressLevelViewModel: StressLevelViewModel,
) : BaseViewModel<DashboardContract.State, DashboardContract.Intent, DashboardContract.SideEffect>(
        initialState = initialState
    ),
    DashboardContract.ViewModel {
    override fun onIntent(intent: DashboardContract.Intent) {
        when (intent) {
            DashboardContract.Intent.Get -> {
                moodViewModel.onIntent(MoodContract.Intent.GetAll)
                userViewModel.onIntent(UserContract.Intent.Get)
                stressLevelViewModel.onIntent(StressLevelContract.Intent.GetAll)
                sleepQualityViewModel.onIntent(SleepQualityContract.Intent.GetAll)
                selfJournalViewModel.onIntent(SelfJournalContract.Intent.GetAll)
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
                healthState.records
            ).anyError(
                block = { error ->
                    viewModelScope.launch {
                        emitEffect(DashboardContract.SideEffect.ShowError(error))
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
            updateState { newState }
        }.launchIn(viewModelScope)
    }
}
