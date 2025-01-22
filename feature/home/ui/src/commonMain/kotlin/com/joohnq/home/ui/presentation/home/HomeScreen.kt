package com.joohnq.home.ui.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.core.ui.IDatetimeProvider
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.freud_score.ui.viewmodel.FreudScoreViewModel
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    onNavigateFreudScore: () -> Unit,
    onNavigateToMood: () -> Unit,
    onNavigateToHealthJournal: () -> Unit,
    onNavigateToMindfulJournal: () -> Unit,
    onNavigateToSleepQuality: () -> Unit,
    onNavigateToStressLevel: () -> Unit,
) {
    val statsViewModel: StatsViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel()
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
    val freudScoreViewModel: FreudScoreViewModel = sharedViewModel()

    val dateTimeProvider: IDatetimeProvider = koinInject()
    val today = dateTimeProvider.formatDate()

    val userState by userViewModel.state.collectAsStateWithLifecycle()
    val freudScoreState by freudScoreViewModel.state.collectAsStateWithLifecycle()
    val statsState by statsViewModel.state.collectAsStateWithLifecycle()
    val sleepQualityState by sleepQualityViewModel.state.collectAsStateWithLifecycle()
    val stressLevelState by stressLevelViewModel.state.collectAsStateWithLifecycle()
    val healthJournalState by healthJournalViewModel.state.collectAsStateWithLifecycle()

    fun onEvent(event: HomeEvent) =
        when (event) {
            HomeEvent.OnNavigateToFreudScore -> onNavigateFreudScore()
            HomeEvent.OnNavigateToMood -> onNavigateToMood()
            HomeEvent.OnNavigateToHealthJournal -> onNavigateToHealthJournal()
            HomeEvent.OnNavigateToMindfulJournal -> onNavigateToMindfulJournal()
            HomeEvent.OnNavigateToSleepQuality -> onNavigateToSleepQuality()
            HomeEvent.OnNavigateToStressLevel -> onNavigateToStressLevel()
        }

    HomeUI(
        today = today,
        user = userState.user,
        statsRecord = statsState.statsRecords,
        freudScore = freudScoreState.freudScore,
        healthJournal = healthJournalState.healthJournalRecords,
        sleepQuality = sleepQualityState.sleepQualityRecords,
        stressLevel = stressLevelState.stressLevelRecords,
        onEvent = ::onEvent
    )
}
