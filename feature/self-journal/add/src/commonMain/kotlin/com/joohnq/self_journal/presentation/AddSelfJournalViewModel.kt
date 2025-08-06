package com.joohnq.self_journal.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.add.ui.mapper.MoodResourceMapper.toDomain
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.use_case.AddSelfJournalsUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class AddSelfJournalViewModel(
    private val addSelfJournalsUseCase: AddSelfJournalsUseCase,
    initialState: AddSelfJournalContract.State = AddSelfJournalContract.State(),
) : BaseViewModel<AddSelfJournalContract.State, AddSelfJournalContract.Intent, AddSelfJournalContract.SideEffect>(
        initialState = initialState
    ),
    AddSelfJournalContract.ViewModel {
    override fun onIntent(intent: AddSelfJournalContract.Intent) {
        when (intent) {
            AddSelfJournalContract.Intent.ResetState ->
                resetState()

            is AddSelfJournalContract.Intent.ChangeDescription ->
                updateState { it.copy(description = intent.description) }

            is AddSelfJournalContract.Intent.ChangeMood ->
                updateState { it.copy(mood = intent.mood) }

            is AddSelfJournalContract.Intent.ChangeTitle ->
                updateState { it.copy(title = intent.title) }

            AddSelfJournalContract.Intent.Add -> add()
        }
    }

    private fun add() {
        viewModelScope.launch {
            if (state.value.mood == null) return@launch

            val record =
                SelfJournalRecord(
                    title = state.value.title,
                    description = state.value.description,
                    mood = state.value.mood!!.toDomain()
                )

            try {
                addSelfJournalsUseCase(record).getOrThrow()

                emitEffect(AddSelfJournalContract.SideEffect.GoBack)
            } catch (e: Exception) {
                emitEffect(AddSelfJournalContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
