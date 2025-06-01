package com.joohnq.freud_score.ui.presentation.freud_score.viewmodel

import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.mood.ui.resource.MoodRecordResource

sealed interface FreudScoreContract {
    sealed interface Intent : FreudScoreContract {
        data class GetFreudScore(val records: List<MoodRecordResource>) : Intent
    }

    data class State(val freudScore: FreudScoreResource? = null) : FreudScoreContract

    sealed interface Event : FreudScoreContract {
        data object GoBack : Event
    }
}