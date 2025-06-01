package com.joohnq.freud_score.ui.presentation.freud_score.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.freud_score.ui.resource.mapper.toResource
import com.joohnq.mood.ui.resource.mapper.calculateStatsFreudScore
import com.joohnq.mood.ui.resource.MoodRecordResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FreudScoreViewModel() :
    ViewModel() {
    private val _state = MutableStateFlow(FreudScoreContract.State())
    val state: StateFlow<FreudScoreContract.State> = _state.asStateFlow()

    fun onIntent(intent: FreudScoreContract.Intent) {
        when (intent) {
            is FreudScoreContract.Intent.GetFreudScore -> getFreudScore(intent.records)
        }
    }

    private fun getFreudScore(records: List<MoodRecordResource>) {
        _state.update { it.copy(freudScore = records.calculateStatsFreudScore().toResource()) }
    }
}