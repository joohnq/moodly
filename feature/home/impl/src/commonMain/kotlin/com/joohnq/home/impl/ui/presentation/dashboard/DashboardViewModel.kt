package com.joohnq.home.impl.ui.presentation.dashboard

import androidx.lifecycle.viewModelScope
import com.joohnq.api.use_case.GetUserUseCase
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.toResource
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.toResource
import com.joohnq.sleep_quality.api.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toResource
import com.joohnq.stress_level.api.use_case.GetAllStressLevelUseCase
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    override fun onIntent(intent: DashboardContract.Intent) {}

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            try {
                async {
                    getUserUseCase()
                        .onEach { user ->
                            updateState {
                                it.copy(
                                    user = user
                                )
                            }
                        }.launchIn(viewModelScope)
                }
                async {
                    getMoodsUseCase()
                        .onEach { items ->
                            val resources = items.toResource()
                            updateState {
                                it.copy(
                                    moodItems = resources
                                )
                            }
                        }.launchIn(viewModelScope)
                }
                async {
                    getSelfJournalsUseCase()
                        .onEach { items ->
                            val resources = items.toResource()
                            updateState {
                                it.copy(
                                    selfJournalItems = resources
                                )
                            }
                        }.launchIn(viewModelScope)
                }
                async {
                    getSleepQualitiesUseCase()
                        .onEach { items ->
                            val resources = items.toResource()
                            updateState {
                                it.copy(
                                    sleepQualityItems = resources
                                )
                            }
                        }.launchIn(viewModelScope)
                }
                async {
                    getAllStressLevelUseCase()
                        .onEach { items ->
                            val resources = items.toResource()
                            updateState {
                                it.copy(
                                    stressLevelItems = resources
                                )
                            }
                        }.launchIn(viewModelScope)
                }
            } catch (e: Exception) {
                emitEffect(DashboardContract.SideEffect.ShowError(e.message.toString()))
            } finally {
                updateState { it.copy(isLoading = false) }
            }
        }
    }
}
