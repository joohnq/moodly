package com.joohnq.freud_score.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.freud_score.ui.FreudScoreResource.Companion.toResource
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.use_case.CalculateStatsFreudScore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FreudScoreViewModel(private val calculateStatsFreudScore: CalculateStatsFreudScore) :
    ViewModel() {
    private val _state = MutableStateFlow(FreudScoreViewModelState())
    val state: StateFlow<FreudScoreViewModelState> =
        _state.asStateFlow()

    fun onAction(intent: FreudScoreViewModelIntent) {
        when (intent) {
            is FreudScoreViewModelIntent.GetFreudScore -> getFreudScore(intent.statsRecords)
        }
    }

    private fun getFreudScore(statsRecords: List<StatsRecord>) {
        _state.update { it.copy(freudScore = calculateStatsFreudScore(statsRecords).toResource()) }
    }
}