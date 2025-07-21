package com.joohnq.freud_score.impl.presentation.freud_score.event

sealed interface FreudScoreEvent {
    data object OnGoBack : FreudScoreEvent
}
