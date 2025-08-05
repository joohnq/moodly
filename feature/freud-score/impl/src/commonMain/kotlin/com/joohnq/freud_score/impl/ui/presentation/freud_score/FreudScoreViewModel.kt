package com.joohnq.freud_score.impl.ui.presentation.freud_score

import com.joohnq.ui.BaseViewModel

class FreudScoreViewModel(
    initialState: FreudScoreContract.State = FreudScoreContract.State(),
) : BaseViewModel<FreudScoreContract.State, FreudScoreContract.Intent, FreudScoreContract.SideEffect>(
        initialState = initialState
    ),
    FreudScoreContract.ViewModel {
    override fun onIntent(intent: FreudScoreContract.Intent) {}
}
