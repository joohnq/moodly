package com.joohnq.freud_score.impl.ui.presentation.freud_score

import com.joohnq.freud_score.impl.ui.resource.FreudScoreResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface FreudScoreContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent

    sealed interface SideEffect {
        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val freudScore: FreudScoreResource? = null,
    )

    sealed interface Event {
        data object GoBack : Event
    }
}
