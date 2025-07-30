package com.joohnq.self_journal.impl.ui.presentation.edit

import androidx.lifecycle.viewModelScope
import com.joohnq.self_journal.api.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalByIdUseCase
import com.joohnq.self_journal.api.use_case.UpdateSelfJournalsUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
import kotlinx.coroutines.launch

class EditSelfJournalViewModel(
    private val getSelfJournalByIdUseCase: GetSelfJournalByIdUseCase,
    private val deleteSelfJournalsUseCase: DeleteSelfJournalsUseCase,
    private val updateSelfJournalsUseCase: UpdateSelfJournalsUseCase,
    initialState: EditSelfJournalContract.State = EditSelfJournalContract.State(),
) : BaseViewModel<EditSelfJournalContract.State, EditSelfJournalContract.Intent, EditSelfJournalContract.SideEffect>(
        initialState = initialState
    ),
    EditSelfJournalContract.ViewModel {
    override fun onIntent(intent: EditSelfJournalContract.Intent) {
        when (intent) {
            EditSelfJournalContract.Intent.ResetState ->
                resetState()

            is EditSelfJournalContract.Intent.UpdateDescription ->
                updateState {
                    it.copy(
                        editingSelfJournalRecord =
                            it.editingSelfJournalRecord.copy(
                                description = intent.description
                            )
                    )
                }

            is EditSelfJournalContract.Intent.UpdateOpenDeleteDialog ->
                updateState { it.copy(openDeleteDialog = intent.value) }

            is EditSelfJournalContract.Intent.UpdateTitle ->
                updateState {
                    it.copy(
                        editingSelfJournalRecord = it.editingSelfJournalRecord.copy(title = intent.title)
                    )
                }

            is EditSelfJournalContract.Intent.UpdateIsEditing ->
                updateState { it.copy(isEditing = intent.value) }

            is EditSelfJournalContract.Intent.Set ->
                updateState {
                    it.copy(
                        currentSelfJournalRecord = intent.record,
                        editingSelfJournalRecord = intent.record
                    )
                }

            EditSelfJournalContract.Intent.ClearEditingState ->
                updateState {
                    it.copy(
                        currentSelfJournalRecord = state.value.editingSelfJournalRecord,
                        editingSelfJournalRecord = state.value.editingSelfJournalRecord
                    )
                }

            is EditSelfJournalContract.Intent.Delete -> delete(intent.id)
            is EditSelfJournalContract.Intent.GetById -> getById(intent.id)
            EditSelfJournalContract.Intent.Update -> update()
        }
    }

    private fun getById(id: Int) =
        viewModelScope.launch {
            val res = getSelfJournalByIdUseCase(id).toUiState()
            res
                .onFailure {
                    emitEffect(EditSelfJournalContract.SideEffect.ShowError(it))
                }
        }

    private fun delete(id: Int) =
        viewModelScope.launch {
            val res = deleteSelfJournalsUseCase(id).toUiState()
            res
                .onSuccess {
                    emitEffect(EditSelfJournalContract.SideEffect.OnGoBack)
                }.onFailure {
                    emitEffect(EditSelfJournalContract.SideEffect.ShowError(it))
                }
        }

    private fun update() =
        viewModelScope.launch {
            val res = updateSelfJournalsUseCase(state.value.editingSelfJournalRecord).toUiState()
            res
                .onSuccess {
                    emitEffect(EditSelfJournalContract.SideEffect.Updated)
                }.onFailure {
                    emitEffect(EditSelfJournalContract.SideEffect.ShowError(it))
                }
        }
}
