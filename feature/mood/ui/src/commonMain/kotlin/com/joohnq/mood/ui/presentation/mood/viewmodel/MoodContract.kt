package com.joohnq.mood.ui.presentation.mood.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.ui.resource.MoodRecordResource

sealed interface MoodContract {
    sealed interface Intent : MoodContract {
        data object GetAll : Intent
        data class Add(val record: MoodRecord) : Intent
        data class Delete(val id: Int) : Intent
    }

    sealed interface SideEffect : MoodContract {
        data object MoodDeleted : SideEffect
        data object MoodAdded : SideEffect
        data class ShowError(val error: Throwable) : SideEffect
    }

    data class State(
        val records: UiState<List<MoodRecordResource>> = UiState.Idle,
    ) : MoodContract

    sealed interface Event {
        data object GoBack : Event
        data object AddMood : Event
        data object NavigateToMoodHistory : Event
    }
}