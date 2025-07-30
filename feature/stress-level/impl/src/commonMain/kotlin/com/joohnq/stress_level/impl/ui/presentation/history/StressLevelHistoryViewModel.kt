package com.joohnq.stress_level.impl.ui.presentation.history

import androidx.lifecycle.viewModelScope
import com.joohnq.stress_level.api.use_case.GetAllStressLevelUseCase
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import kotlinx.coroutines.launch

class StressLevelHistoryViewModel(
    private val getAllStressLevelUseCase: GetAllStressLevelUseCase,
    initialState: StressLevelHistoryContract.State = StressLevelHistoryContract.State(),
) : BaseViewModel<StressLevelHistoryContract.State, StressLevelHistoryContract.Intent, StressLevelHistoryContract.SideEffect>(
        initialState = initialState
    ),
    StressLevelHistoryContract.ViewModel {
    override fun onIntent(intent: StressLevelHistoryContract.Intent) {
        when (intent) {
            StressLevelHistoryContract.Intent.GetAll -> getAll()
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }

            val res =
                getAllStressLevelUseCase()
                    .toResultResource { it.toResource() }
                    .toUiState()

            updateState { it.copy(res) }
        }
    }
}
