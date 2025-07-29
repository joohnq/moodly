package com.joohnq.self_journal.impl.ui.presentation.self_journal_history

import com.joohnq.api.getNow
import com.joohnq.ui.UnidirectionalViewModel
import kotlinx.datetime.LocalDate

interface SelfJournalHistoryContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data object ResetState : Intent

        data class UpdateSelectedDateTime(
            val selectedDateTime: LocalDate,
        ) : Intent
    }

    data class State(
        val selectedDateTime: LocalDate = getNow().date,
    )

    sealed interface SideEffect

    sealed interface Event {
        data object OnGoBack : Event

        data class OnSelectJournalHistory(
            val id: Int,
        ) : Event

        data class OnDelete(
            val id: Int,
        ) : Event
    }
}
