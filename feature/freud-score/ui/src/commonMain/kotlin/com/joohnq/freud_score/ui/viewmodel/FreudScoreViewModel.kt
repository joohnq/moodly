package com.joohnq.freud_score.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.freud_score.ui.mapper.toResource
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.use_case.CalculateStatsFreudScoreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FreudScoreViewModel(private val calculateStatsFreudScoreUseCase: CalculateStatsFreudScoreUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(FreudScoreState())
    val state: StateFlow<FreudScoreState> = _state.asStateFlow()

    fun onAction(intent: FreudScoreIntent) {
        when (intent) {
            is FreudScoreIntent.GetFreudScore -> getFreudScore(intent.statsRecords)
        }
    }

    private fun getFreudScore(statsRecords: List<StatsRecord>) {
        _state.update { it.copy(freudScore = calculateStatsFreudScoreUseCase(statsRecords).toResource()) }
    }
}