package com.joohnq.gratefulness.add.presentation

import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.ui.UnidirectionalViewModel

sealed interface GratefulnessAddContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class ChangeIAmGratefulFor(
            val value: String,
        ) : Intent

        data class ChangeSmallThingIAppreciate(
            val value: String,
        ) : Intent

        data object Add : Intent
    }

    sealed interface SideEffect {
        data object NavigateToGratefulnessOverview : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val item: Gratefulness = Gratefulness(),
    )

    sealed interface Event {
        data object GoBack :
            Event
    }
}
