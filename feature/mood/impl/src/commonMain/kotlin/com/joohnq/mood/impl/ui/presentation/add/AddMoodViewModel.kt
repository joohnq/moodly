package com.joohnq.mood.impl.ui.presentation.add

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.api.use_case.AddMoodUseCase
import com.joohnq.mood.impl.ui.mapper.MoodRecordResourceMapper.toDomain
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
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
            is AddMoodContract.Intent.UpdateAddingMoodRecordMood ->
                updateState { it.copy(record = it.record.copy(mood = intent.mood)) }

            is AddMoodContract.Intent.UpdateAddingMoodRecordDescription ->
                updateState { it.copy(record = it.record.copy(description = intent.description)) }

            AddMoodContract.Intent.ResetState -> updateState { initialState }
            AddMoodContract.Intent.Add -> add()
        }
    }

    private fun add() =
        viewModelScope.launch {
            val res = addMoodUseCase(state.value.record.toDomain()).toUiState()
            res
                .onSuccess {
                    emitEffect(AddMoodContract.SideEffect.StatsAdded)
                }.onFailure {
                    emitEffect(AddMoodContract.SideEffect.ShowError(it))
                }
        }
}
