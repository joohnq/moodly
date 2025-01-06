package com.joohnq.home.ui.presentation.home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.IDatetimeProvider
import com.joohnq.core.ui.mapper.getValue
import com.joohnq.core.ui.mapper.onAnyError
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.freud_score.ui.viewmodel.FreudScoreViewModel
import com.joohnq.freud_score.ui.viewmodel.FreudScoreViewModelIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.home.ui.presentation.home.state.HomeState
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModelIntent
import kotlinx.coroutines.launch
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
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val statsViewModel: StatsViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel()
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
    val freudScoreViewModel: FreudScoreViewModel = sharedViewModel()

    val dateTimeProvider: IDatetimeProvider = koinInject()
    val today = dateTimeProvider.formatDate()

    val userState by userViewModel.state.collectAsState()
    val freudScoreState by freudScoreViewModel.state.collectAsState()
    val statsState by statsViewModel.state.collectAsState()
    val sleepQualityState by sleepQualityViewModel.state.collectAsState()
    val stressLevelState by stressLevelViewModel.state.collectAsState()
    val healthJournalState by healthJournalViewModel.state.collectAsState()

    fun onError(error: String) {
        scope.launch {
            snackBarHostState.showSnackbar(error)
        }
    }

    fun onEvent(event: HomeEvent) =
        when (event) {
            HomeEvent.OnNavigateToFreudScore -> onNavigateFreudScore()
            HomeEvent.OnNavigateToMood -> onNavigateToMood()
            HomeEvent.OnNavigateToHealthJournal -> onNavigateToHealthJournal()
            HomeEvent.OnNavigateToMindfulJournal -> onNavigateToMindfulJournal()
            HomeEvent.OnNavigateToSleepQuality -> onNavigateToSleepQuality()
            HomeEvent.OnNavigateToStressLevel -> onNavigateToStressLevel()
        }

    SideEffect {
        statsViewModel.onAction(StatsIntent.GetStatsRecords)
        userViewModel.onAction(UserViewModelIntent.GetUser)
        stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)
        sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
        healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
    }

    LaunchedEffect(statsState.statsRecords) {
        statsState.statsRecords.onSuccess {
            freudScoreViewModel.onAction(FreudScoreViewModelIntent.GetFreudScore(statsState.statsRecords.getValue()))
        }
    }

    LaunchedEffect(
        statsState.statsRecords,
        userState.user,
        stressLevelState.stressLevelRecords,
        sleepQualityState.sleepQualityRecords,
        healthJournalState.healthJournalRecords,
    ) {
        onAnyError(
            statsState.statsRecords,
            userState.user,
            stressLevelState.stressLevelRecords,
            sleepQualityState.sleepQualityRecords,
            sleepQualityState.sleepQualityRecords,
            onAnyHasError = ::onError
        )
    }

    HomeUI(
        HomeState(
            today = today,
            user = userState.user,
            statsRecord = statsState.statsRecords,
            freudScore = freudScoreState.freudScore,
            healthJournal = healthJournalState.healthJournalRecords,
            sleepQuality = sleepQualityState.sleepQualityRecords,
            stressLevel = stressLevelState.stressLevelRecords,
            onEvent = ::onEvent
        )
    )
}
