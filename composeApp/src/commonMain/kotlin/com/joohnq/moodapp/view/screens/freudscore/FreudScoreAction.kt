package com.joohnq.moodapp.view.screens.freudscore

import com.joohnq.moodapp.entities.StatsRecord

sealed class FreudScoreAction {
    data object OnGoBack : FreudScoreAction()
    data class OnNavigateToMood(val statsRecord: StatsRecord) : FreudScoreAction()
    data object OnAdd : FreudScoreAction()
}