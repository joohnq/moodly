package com.joohnq.freud_score.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.freud_score.domain.mapper.toFreudScore
import com.joohnq.freud_score.ui.mapper.toResource
import com.joohnq.mood.ui.resource.MoodRecordResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

fun List<MoodRecordResource?>.calculateStatsFreudScore(): FreudScore {
    val score = sumOf { it?.mood?.healthLevel ?: 0 } / size
    return score.toFreudScore()
}

class FreudScoreViewModel() :
    ViewModel() {
    private val _state = MutableStateFlow(FreudScoreState())
    val state: StateFlow<FreudScoreState> = _state.asStateFlow()

    fun onAction(intent: FreudScoreIntent) {
        when (intent) {
            is FreudScoreIntent.GetFreudScore -> getFreudScore(intent.records)
        }
    }

    private fun getFreudScore(records: List<MoodRecordResource>) {
        _state.update { it.copy(freudScore = records.calculateStatsFreudScore().toResource()) }
    }
}