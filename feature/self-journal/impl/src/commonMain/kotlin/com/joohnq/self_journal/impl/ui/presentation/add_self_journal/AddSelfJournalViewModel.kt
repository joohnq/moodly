package com.joohnq.self_journal.impl.ui.presentation.add_self_journal

import com.joohnq.ui.BaseViewModel

class AddSelfJournalViewModel(
    initialState: AddSelfJournalContract.State = AddSelfJournalContract.State(),
) : BaseViewModel<AddSelfJournalContract.State, AddSelfJournalContract.Intent, AddSelfJournalContract.SideEffect>(
    initialState = initialState
), AddSelfJournalContract.ViewModel {
    override fun onIntent(intent: AddSelfJournalContract.Intent) {
        when (intent) {
            AddSelfJournalContract.Intent.ResetState ->
                updateState { AddSelfJournalContract.State() }

            is AddSelfJournalContract.Intent.UpdateDescription ->
                updateState { it.copy(description = intent.description) }

            is AddSelfJournalContract.Intent.UpdateMood ->
                updateState { it.copy(mood = intent.mood) }

            is AddSelfJournalContract.Intent.UpdateTitle ->
                updateState { it.copy(title = intent.title) }

            is AddSelfJournalContract.Intent.UpdateTitleError ->
                updateState { it.copy(titleError = intent.error) }
        }
    }
}