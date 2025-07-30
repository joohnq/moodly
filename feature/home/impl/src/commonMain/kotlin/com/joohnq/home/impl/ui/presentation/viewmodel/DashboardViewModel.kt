package com.joohnq.home.impl.ui.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreContract
import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreViewModel
import com.joohnq.mood.impl.ui.presentation.overview.MoodOverviewContract
import com.joohnq.mood.impl.ui.presentation.overview.MoodOverviewViewModel
import com.joohnq.self_journal.impl.ui.presentation.overview.SelfJournalOverviewContract
import com.joohnq.self_journal.impl.ui.presentation.overview.SelfJournalOverviewViewModel
import com.joohnq.sleep_quality.impl.ui.presentation.overview.SleepQualityOverviewContract
import com.joohnq.sleep_quality.impl.ui.presentation.overview.SleepQualityOverviewViewModel
import com.joohnq.stress_level.impl.ui.presentation.overview.StressLevelOverviewContract
import com.joohnq.stress_level.impl.ui.presentation.overview.StressLevelOverviewViewModel
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.mapper.UiStateMapper.anyError
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
import com.joohnq.user.impl.ui.viewmodel.UserContract
import com.joohnq.user.impl.ui.viewmodel.UserViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DashboardViewModel(
    initialState: DashboardContract.State = DashboardContract.State(),
    private val userViewModel: UserViewModel,
    private val moodOverviewViewModel: MoodOverviewViewModel,
    private val freudScoreViewModel: FreudScoreViewModel,
    private val selfJournalOverviewViewModel: SelfJournalOverviewViewModel,
    private val sleepQualityOverviewViewModel: SleepQualityOverviewViewModel,
    private val stressLevelOverviewViewModel: StressLevelOverviewViewModel,
) : BaseViewModel<DashboardContract.State, DashboardContract.Intent, DashboardContract.SideEffect>(
        initialState = initialState
    ),
    DashboardContract.ViewModel {
    override fun onIntent(intent: DashboardContract.Intent) {
        when (intent) {
            DashboardContract.Intent.Get -> {
                moodOverviewViewModel.onIntent(MoodOverviewContract.Intent.GetAll)
                userViewModel.onIntent(UserContract.Intent.Get)
                stressLevelOverviewViewModel.onIntent(StressLevelOverviewContract.Intent.GetAll)
                sleepQualityOverviewViewModel.onIntent(SleepQualityOverviewContract.Intent.GetAll)
                selfJournalOverviewViewModel.onIntent(SelfJournalOverviewContract.Intent.GetAll)
            }
        }
    }

    init {
        combine(
            userViewModel.state,
            moodOverviewViewModel.state,
            freudScoreViewModel.state,
            selfJournalOverviewViewModel.state,
            sleepQualityOverviewViewModel.state,
            stressLevelOverviewViewModel.state
        ) { states ->
            val state = states[0] as UserContract.State
            val statsState = states[1] as MoodOverviewContract.State
            val freudState = states[2] as FreudScoreContract.State
            val healthState = states[3] as SelfJournalOverviewContract.State
            val sleepState = states[4] as SleepQualityOverviewContract.State
            val stressState = states[5] as StressLevelOverviewContract.State

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
