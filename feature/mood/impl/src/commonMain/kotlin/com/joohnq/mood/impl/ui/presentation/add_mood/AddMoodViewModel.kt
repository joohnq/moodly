package com.joohnq.mood.impl.ui.presentation.add_mood

import com.joohnq.ui.BaseViewModel

class AddMoodViewModel(
    private val initialState: AddMoodContract.State = AddMoodContract.State(),
) : BaseViewModel<AddMoodContract.State, AddMoodContract.Intent, AddMoodContract.SideEffect>(
    initialState = initialState
), AddMoodContract.ViewModel {
    override fun onIntent(intent: AddMoodContract.Intent) {
        when (intent) {
            is AddMoodContract.Intent.UpdateAddingMoodRecordMood ->
                updateState { it.copy(record = it.record.copy(mood = intent.mood)) }

            is AddMoodContract.Intent.UpdateAddingMoodRecordDescription ->
                updateState { it.copy(record = it.record.copy(description = intent.description)) }

            AddMoodContract.Intent.ResetState -> updateState { initialState }
        }
    }
}