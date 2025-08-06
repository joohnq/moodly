package com.joohnq.add.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.toDomain
import com.joohnq.mood.api.use_case.AddMoodUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class AddMoodViewModel(
    private val addMoodUseCase: AddMoodUseCase,
    private val initialState: AddMoodContract.State = AddMoodContract.State(),
) : BaseViewModel<AddMoodContract.State, AddMoodContract.Intent, AddMoodContract.SideEffect>(
        initialState = initialState
    ),
    AddMoodContract.ViewModel {
    override fun onIntent(intent: AddMoodContract.Intent) {
        when (intent) {
            is AddMoodContract.Intent.ChangeMood ->
                updateState { it.copy(record = it.record.copy(mood = intent.mood)) }

            is AddMoodContract.Intent.ChangeDescription ->
                updateState { it.copy(record = it.record.copy(description = intent.description)) }

            AddMoodContract.Intent.ResetState -> updateState { initialState }
            AddMoodContract.Intent.Add -> add()
        }
    }

    private fun add() =
        viewModelScope.launch {
            try {
                addMoodUseCase(state.value.record.toDomain()).getOrThrow()

                emitEffect(AddMoodContract.SideEffect.NavigateNext)
            } catch (e: Exception) {
                emitEffect(AddMoodContract.SideEffect.ShowError(e.message.toString()))
            }
        }
}
