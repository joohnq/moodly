package com.joohnq.home.impl.ui.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.joohnq.api.use_case.GetUserUseCase
import com.joohnq.freud_score.impl.ui.mapper.FreudScoreResourceMapper.toResource
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.calculateStatsFreudScore
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.toResource
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.toResource
import com.joohnq.sleep_quality.api.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toResource
import com.joohnq.stress_level.api.use_case.GetAllStressLevelUseCase
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getMoodsUseCase: GetMoodsUseCase,
    private val getSelfJournalsUseCase: GetSelfJournalsUseCase,
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
    private val getAllStressLevelUseCase: GetAllStressLevelUseCase,
    initialState: DashboardContract.State = DashboardContract.State(),
) : BaseViewModel<DashboardContract.State, DashboardContract.Intent, DashboardContract.SideEffect>(
        initialState = initialState
    ),
    DashboardContract.ViewModel {
    override fun onIntent(intent: DashboardContract.Intent) {
        when (intent) {
            DashboardContract.Intent.Get -> get()
        }
    }

    private fun get() {
        viewModelScope.launch {
            try {
                val userDeferred = async { getUserUseCase() }
                val moodsDeferred = async { getMoodsUseCase() }
                val selfJournalsDeferred = async { getSelfJournalsUseCase() }
                val sleepQualitiesDeferred = async { getSleepQualitiesUseCase() }
                val stressLevelsDeferred = async { getAllStressLevelUseCase() }

                val user = userDeferred.await()
                val moods = moodsDeferred.await().toResultResource { it.toResource() }
                val freudScore = moods.getOrThrow().calculateStatsFreudScore().toResource()
                val selfJournals = selfJournalsDeferred.await().toResultResource { it.toResource() }
                val sleepQualities =
                    sleepQualitiesDeferred.await().toResultResource { it.toResource() }
                val stressLevels = stressLevelsDeferred.await().toResultResource { it.toResource() }

                updateState {
                    it.copy(
                        user = user.toUiState(),
                        freudScore = freudScore,
                        moodRecords = moods.toUiState(),
                        selfJournalRecords = selfJournals.toUiState(),
                        sleepQualityRecords = sleepQualities.toUiState(),
                        stressLevelRecords = stressLevels.toUiState()
                    )
                }
            } catch (e: Exception) {
                emitEffect(DashboardContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
