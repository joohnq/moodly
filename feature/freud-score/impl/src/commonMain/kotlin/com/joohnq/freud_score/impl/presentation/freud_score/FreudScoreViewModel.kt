package com.joohnq.freud_score.impl.presentation.freud_score

import androidx.lifecycle.ViewModel
import com.joohnq.freud_score.impl.mapper.toResource
import com.joohnq.mood.impl.ui.mapper.calculateStatsFreudScore
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FreudScoreViewModel() :
    ViewModel() {
    private val _state = MutableStateFlow(FreudScoreContract.State())
    val state: StateFlow<FreudScoreContract.State> = _state.asStateFlow()

    fun onAction(intent: FreudScoreContract.Intent) {
        when (intent) {
            is FreudScoreContract.Intent.Get -> getFreudScore(intent.records)
        }
    }

    private fun getFreudScore(records: List<MoodRecordResource>) {
        _state.update { it.copy(freudScore = records.calculateStatsFreudScore().toResource()) }
    }
}