package com.joohnq.freud_score.impl.presentation.freud_score

import com.joohnq.freud_score.impl.mapper.toResource
import com.joohnq.mood.impl.ui.mapper.calculateStatsFreudScore
import com.joohnq.ui.BaseViewModel

class FreudScoreViewModel(
    initialState: FreudScoreContract.State = FreudScoreContract.State()
) : BaseViewModel<FreudScoreContract.State, FreudScoreContract.Intent, FreudScoreContract.SideEffect>(
    initialState = initialState
),
    FreudScoreContract.ViewModel {
    override fun onIntent(intent: FreudScoreContract.Intent) {
        when (intent) {
            is FreudScoreContract.Intent.Get ->
                updateState {
                    it.copy(freudScore = intent.records.calculateStatsFreudScore().toResource())
                }
        }
    }
}
