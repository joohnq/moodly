package com.joohnq.self_journal.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.self_journal.api.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalByIdUseCase
import com.joohnq.self_journal.api.use_case.UpdateSelfJournalsUseCase
import com.joohnq.ui.BaseViewModel
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

            is EditSelfJournalContract.Intent.ChangeDescription ->
                updateState {
                    it.copy(
                        editingSelfJournalRecord =
                            it.editingSelfJournalRecord.copy(
                                description = intent.description
                            )
                    )
                }

            is EditSelfJournalContract.Intent.ChangeOpenDeleteDialog ->
                updateState { it.copy(openDeleteDialog = intent.value) }

            is EditSelfJournalContract.Intent.ChangeTitle ->
                updateState {
                    it.copy(
                        editingSelfJournalRecord = it.editingSelfJournalRecord.copy(title = intent.title)
                    )
                }

            is EditSelfJournalContract.Intent.ChangeIsEditing ->
                updateState { it.copy(isEditing = intent.value) }

            EditSelfJournalContract.Intent.ClearEditingState ->
                updateState {
                    it.copy(
                        currentSelfJournalRecord = state.value.editingSelfJournalRecord,
                        editingSelfJournalRecord = state.value.editingSelfJournalRecord
                    )
                }

            is EditSelfJournalContract.Intent.Delete -> delete(intent.id)
            is EditSelfJournalContract.Intent.GetById -> getById(intent.id)
            EditSelfJournalContract.Intent.Action -> update()
        }
    }

    private fun getById(id: Int) =
        viewModelScope.launch {
            try {
                val res = getSelfJournalByIdUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        currentSelfJournalRecord = res,
                        editingSelfJournalRecord = res
                    )
                }
            } catch (e: Exception) {
                emitEffect(EditSelfJournalContract.SideEffect.ShowError(e.message.toString()))
            }
        }

    private fun delete(id: Int) =
        viewModelScope.launch {
            try {
                deleteSelfJournalsUseCase(id).getOrThrow()

                emitEffect(EditSelfJournalContract.SideEffect.GoBack)
            } catch (e: Exception) {
                emitEffect(EditSelfJournalContract.SideEffect.ShowError(e.message.toString()))
            }
        }

    private fun update() =
        viewModelScope.launch {
            try {
                updateSelfJournalsUseCase(state.value.editingSelfJournalRecord).getOrThrow()

                onIntent(EditSelfJournalContract.Intent.ClearEditingState)

                onIntent(
                    EditSelfJournalContract.Intent.ChangeIsEditing(
                        false
                    )
                )
            } catch (e: Exception) {
                emitEffect(EditSelfJournalContract.SideEffect.ShowError(e.message.toString()))
            }
        }
}
