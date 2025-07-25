package com.joohnq.freud_score.impl.presentation.freud_score

import com.joohnq.freud_score.impl.resource.FreudScoreResource
import com.joohnq.mood.impl.ui.resource.MoodRecordResource

sealed interface FreudScoreContract {
    sealed interface Intent {
        data class Get(val records: List<MoodRecordResource>) : Intent
    }

    data class State(
        val freudScore: FreudScoreResource? = null,
    )

    sealed interface Event {
        data object OnGoBack : Event
    }
}