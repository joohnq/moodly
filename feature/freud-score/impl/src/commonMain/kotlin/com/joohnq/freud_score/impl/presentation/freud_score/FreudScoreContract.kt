package com.joohnq.freud_score.impl.presentation.freud_score

import com.joohnq.freud_score.impl.resource.FreudScoreResource
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface FreudScoreContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class Get(val records: List<MoodRecordResource>) : Intent
    }

    sealed interface SideEffect

    data class State(
        val freudScore: FreudScoreResource? = null
    )

    sealed interface Event {
        data object OnGoBack : Event
    }
}
