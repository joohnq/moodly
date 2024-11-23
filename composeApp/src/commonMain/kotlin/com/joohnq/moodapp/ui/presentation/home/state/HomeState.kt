package com.joohnq.moodapp.ui.presentation.home.state

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.FreudScore
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.SleepQuality
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.ui.presentation.home.event.HomeEvent

data class HomeState(
    val today: String = "",
    val padding: PaddingValues = PaddingValues(0.dp),
    val userName: String,
    val statsRecord: StatsRecord,
    val sleepQuality: SleepQuality,
    val stressLevel: StressLevel,
    val freudScore: FreudScore,
    val moodTracker: List<Mood>,
    val healthJournal: List<HealthJournalRecord>,
    val onEvent: (HomeEvent) -> Unit = {},
)