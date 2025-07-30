package com.joohnq.self_journal.impl.ui.presentation.history

import com.joohnq.api.getNow
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState
import kotlinx.datetime.LocalDate

interface SelfJournalHistoryContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data object ResetState : Intent

        data object GetAll : Intent

        data class Delete(
            val id: Int,
        ) : Intent

        data class UpdateSelectedDateTime(
            val selectedDateTime: LocalDate,
        ) : Intent
    }

    data class State(
        val records: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
        val selectedDateTime: LocalDate = getNow().date,
    )

    sealed interface SideEffect {
        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    sealed interface Event {
        data object OnGoBack : Event

        data class OnSelectJournalHistory(
            val id: Int,
        ) : Event
    }
}
