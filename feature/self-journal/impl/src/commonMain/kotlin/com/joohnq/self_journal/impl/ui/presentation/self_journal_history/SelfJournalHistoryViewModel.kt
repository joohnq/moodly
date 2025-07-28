package com.joohnq.self_journal.impl.ui.presentation.self_journal_history

import com.joohnq.ui.BaseViewModel

class SelfJournalHistoryViewModel(
    initialState: SelfJournalHistoryContract.State = SelfJournalHistoryContract.State(),
) : BaseViewModel<
        SelfJournalHistoryContract.State,
        SelfJournalHistoryContract.Intent,
        SelfJournalHistoryContract.SideEffect
    >(
        initialState = initialState
    ),
    SelfJournalHistoryContract.ViewModel {
    override fun onIntent(intent: SelfJournalHistoryContract.Intent) {
        when (intent) {
            is SelfJournalHistoryContract.Intent.UpdateCurrentDeleteId ->
                updateState { it.copy(currentDeleteId = intent.id) }

            is SelfJournalHistoryContract.Intent.UpdateOpenDeleteDialog ->
                updateState { it.copy(openDeleteDialog = intent.openDeleteDialog) }

            is SelfJournalHistoryContract.Intent.UpdateSelectedDateTime ->
                updateState { it.copy(selectedDateTime = intent.selectedDateTime) }

            is SelfJournalHistoryContract.Intent.ResetState ->
                updateState { SelfJournalHistoryContract.State() }
        }
    }
}
