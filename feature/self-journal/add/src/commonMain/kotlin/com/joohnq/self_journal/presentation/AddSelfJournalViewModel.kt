package com.joohnq.self_journal.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.impl.ui.mapper.MoodResourceMapper.toDomain
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.use_case.AddSelfJournalsUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
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

            is AddSelfJournalContract.Intent.UpdateDescription ->
                updateState { it.copy(description = intent.description) }

            is AddSelfJournalContract.Intent.UpdateMood ->
                updateState { it.copy(mood = intent.mood) }

            is AddSelfJournalContract.Intent.UpdateTitle ->
                updateState { it.copy(title = intent.title) }

            is AddSelfJournalContract.Intent.UpdateTitleError ->
                updateState { it.copy(titleError = intent.error) }

            AddSelfJournalContract.Intent.Add -> add()
        }
    }

    private fun add() =
        viewModelScope.launch {
            if (state.value.mood == null) return@launch

            val record =
                SelfJournalRecord(
                    title = state.value.title,
                    description = state.value.description,
                    mood = state.value.mood!!.toDomain()
                )

            val res = addSelfJournalsUseCase(record).toUiState()

            res
                .onSuccess {
                    emitEffect(AddSelfJournalContract.SideEffect.OnGoBack)
                }.onFailure {
                    emitEffect(AddSelfJournalContract.SideEffect.ShowError(it))
                }
        }
}