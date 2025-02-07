package com.joohnq.freud_score.ui.presentation.freud_score.event

sealed interface FreudScoreEvent {
    data object OnGoBack : FreudScoreEvent
}
