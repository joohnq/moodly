package com.joohnq.home.ui.presentation.dashboard

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.joohnq.core.ui.mapper.anyError
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.freud_score.ui.viewmodel.FreudScoreIntent
import com.joohnq.freud_score.ui.viewmodel.FreudScoreViewModel
import com.joohnq.health_journal.ui.presentation.journaling.JournalingScreen
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.home.ui.components.DashboardBottomNavigation
import com.joohnq.home.ui.presentation.dashboard.event.DashboardEvent
import com.joohnq.home.ui.presentation.home.HomeScreen
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.navigation.Destination
import com.joohnq.navigation.isCurrentRoute
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.viewmodel.user.UserIntent
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    onEvent: (DashboardEvent) -> Unit
) {
    val snackBarHostState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val navigator = rememberNavController()
    val navBackStackEntry by navigator.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hierarchy = currentDestination?.hierarchy

    var centralIsExpanded by remember { mutableStateOf(false) }

    val statsViewModel: StatsViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel()
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
    val freudScoreViewModel: FreudScoreViewModel = sharedViewModel()

    val statsState by statsViewModel.state.collectAsStateWithLifecycle()
    val userState by userViewModel.state.collectAsStateWithLifecycle()
    val sleepQualityState by sleepQualityViewModel.state.collectAsStateWithLifecycle()
    val stressLevelState by stressLevelViewModel.state.collectAsStateWithLifecycle()
    val healthJournalState by healthJournalViewModel.state.collectAsStateWithLifecycle()

    fun onError(error: Throwable) {
        scope.launch {
            snackBarHostState.showSnackbar(error.message.toString())
        }
    }

    fun onNavigateBottomNavigate(destination: Destination) {
        if (!hierarchy.isCurrentRoute(destination))
            navigator.navigate(destination)

        centralIsExpanded = false
    }

    LaunchedEffect(statsState.statsRecords) {
        statsState.statsRecords.onSuccess { records ->
            freudScoreViewModel.onAction(
                FreudScoreIntent.GetFreudScore(records)
            )
        }
    }

    LaunchedEffect(Unit) {
        statsViewModel.onAction(StatsIntent.GetStatsRecords)
        userViewModel.onAction(UserIntent.GetUser)
        stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)
        sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
        healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
    }

    LaunchedEffect(
        statsState.statsRecords,
        userState.user,
        stressLevelState.stressLevelRecords,
        sleepQualityState.sleepQualityRecords,
        healthJournalState.healthJournalRecords,
    ) {
        listOf(
            statsState.statsRecords,
            userState.user,
            stressLevelState.stressLevelRecords,
            sleepQualityState.sleepQualityRecords,
            sleepQualityState.sleepQualityRecords,
        ).anyError(
            block = ::onError
        )
    }

    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        snackBarHostState = snackBarHostState,
        bottomBar = {
            DashboardBottomNavigation(
                isCurrentRoute = hierarchy::isCurrentRoute,
                onNavigate = ::onNavigateBottomNavigate,
                isCentralExpanded = centralIsExpanded,
                toggleIsCentralExpanded = { centralIsExpanded = !centralIsExpanded },
            )
        }
    ) { padding ->
        NavHost(navigator, startDestination = Destination.App.DashBoard.Home) {
            composable<Destination.App.DashBoard.Home> {
                HomeScreen(
                    padding = padding,
                    onError = ::onError,
                    onNavigateFreudScore = { onEvent(DashboardEvent.OnNavigateToFreudScore) },
                    onNavigateToMood = { onEvent(DashboardEvent.OnNavigateToMood) },
                    onNavigateToHealthJournal = { onEvent(DashboardEvent.OnNavigateToHealthJournal) },
                    onNavigateToMindfulJournal = { onEvent(DashboardEvent.OnNavigateToMindfulJournal) },
                    onNavigateToSleepQuality = { onEvent(DashboardEvent.OnNavigateToSleepQuality) },
                    onNavigateToStressLevel = { onEvent(DashboardEvent.OnNavigateToStressLevel) },
                    onNavigateToAddSleep = { onEvent(DashboardEvent.OnNavigateToAddSleep) },
                    onNavigateToAddStress = { onEvent(DashboardEvent.OnNavigateToAddStress) },
                    onNavigateToSelfJournalHistory = { onEvent(DashboardEvent.OnNavigateToSelfJournalHistory) },
                    onNavigateToAddJournaling = { onEvent(DashboardEvent.OnNavigateToAddJournaling) },
                    onNavigateAddStat = { onEvent(DashboardEvent.OnNavigateToAddStat) },
                )
            }
            composable<Destination.App.DashBoard.Journaling> {
                JournalingScreen(
                    padding = padding,
                    onNavigateToEditJournaling = { id -> onEvent(DashboardEvent.OnNavigateToEditJournaling(id)) },
                    onNavigateToAllJournals = { onEvent(DashboardEvent.OnNavigateToSelfJournalHistory) }
                )
            }
        }
    }
}
