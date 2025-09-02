package com.joohnq.self_journal.presentation

import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.getGrouped
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.getSelfJournalsInYear
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface SelfJournalOverviewContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class Delete(
            val id: Long,
        ) : Intent
    }

    sealed interface SideEffect {
        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val items: List<SelfJournalRecordResource> = listOf(),
        val isLoading: Boolean = false,
        val isError: String? = null,
    ) {
        val groupedInsightItems: List<Pair<MoodResource, Int>>
            get() = items.getGrouped()

        val itemsInYear: String
            get() = items.getSelfJournalsInYear()

        val historyItems: List<SelfJournalRecordResource>
            get() = items.take(7)
    }

    sealed interface Event {
        data object GoBack :
            Event

        data object NavigateToAddSelfJournal :
            Event

        data object NavigateToSelfJournalHistory :
            Event

        data class NavigateToEditSelfJournal(
            val id: Long,
        ) : Event
    }
}
