package com.joohnq.home.ui.presentation.dashboard

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.joohnq.core.ui.entity.DIcon
import com.joohnq.core.ui.mapper.anyError
import com.joohnq.core.ui.mapper.getValueOrNull
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.freud_score.ui.viewmodel.FreudScoreIntent
import com.joohnq.freud_score.ui.viewmodel.FreudScoreViewModel
import com.joohnq.health_journal.ui.presentation.journaling.JournalingScreen
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.home.ui.BottomItem
import com.joohnq.home.ui.components.DashboardBottomNavigation
import com.joohnq.home.ui.components.TabItem
import com.joohnq.home.ui.presentation.home.HomeScreen
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.home
import com.joohnq.shared_resources.journaling
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.viewmodel.user.UserIntent
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    onNavigateAddJournaling: () -> Unit,
    onNavigateAddStatScreen: () -> Unit,
    onNavigateFreudScore: () -> Unit,
    onNavigateToMood: () -> Unit,
    onNavigateToHealthJournal: () -> Unit,
    onNavigateToMindfulJournal: () -> Unit,
    onNavigateToSleepHistory: () -> Unit,
    onNavigateToStressLevel: (Int) -> Unit,
    onNavigateToStressHistory: () -> Unit,
    onNavigateToEditJournaling: (Int) -> Unit,
    onNavigateToSelfJournalHistory: () -> Unit,
    onNavigateToAddSleep: () -> Unit,
    onNavigateToAddStress: () -> Unit,
    onNavigateToAddJournaling: () -> Unit,
) {
    val snackBarHostState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    var addButtonIsExpanded by remember { mutableStateOf(false) }
    val navigator = rememberNavController()
    val navBackStackEntry by navigator.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
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

    val bottomItems = remember {
        listOf(
            BottomItem(
                icon = DIcon(
                    icon = Drawables.Icons.Home,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.home
                ),
                title = Res.string.home,
                route = Destination.App.DashBoard.Home
            ),
            BottomItem(
                icon = DIcon(
                    icon = Drawables.Icons.Document,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.journaling
                ),
                title = Res.string.journaling,
                route = Destination.App.DashBoard.Journaling,
            ),
        )
    }

    fun onError(error: Throwable) {
        scope.launch {
            snackBarHostState.showSnackbar(error.message.toString())
        }
    }

    fun isCurrentRoute(route: String?): Boolean =
        currentDestination?.hierarchy?.any { it.route == route } == true

    fun onNavigate(destination: Destination) {
        if (!isCurrentRoute(destination::class.qualifiedName))
            navigator.navigate(destination)

        addButtonIsExpanded = false
    }

    LaunchedEffect(statsState.statsRecords) {
        statsState.statsRecords.onSuccess {
            freudScoreViewModel.onAction(
                FreudScoreIntent.GetFreudScore(
                    statsState.statsRecords.getValueOrNull()
                )
            )
        }
    }

    @Composable
    fun BottomItem<out Destination>.createTabItem() {
        val isSelected = isCurrentRoute(route::class.qualifiedName)
        return TabItem(
            icon = icon,
            selected = isSelected,
            onNavigate = { onNavigate(route) }
        )
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
        bottomBar = {
            DashboardBottomNavigation(
                isExpanded = addButtonIsExpanded,
                switchIsExpanded = { addButtonIsExpanded = !addButtonIsExpanded },
                left = {
                    bottomItems[0].createTabItem()
                },
                right = {
                    bottomItems[1].createTabItem()
                },
                onNavigateAddJournaling = onNavigateAddJournaling,
                onNavigateAddStatScreen = onNavigateAddStatScreen,
            )
        },
        snackBarHostState = snackBarHostState,
    ) { _ ->
        NavHost(navigator, startDestination = Destination.App.DashBoard.Home) {
            composable<Destination.App.DashBoard.Home> {
                HomeScreen(
                    onNavigateFreudScore = onNavigateFreudScore,
                    onNavigateToMood = onNavigateToMood,
                    onNavigateToHealthJournal = onNavigateToHealthJournal,
                    onNavigateToMindfulJournal = onNavigateToMindfulJournal,
                    onNavigateToSleepHistory = onNavigateToSleepHistory,
                    onNavigateToStressLevel = onNavigateToStressLevel,
                    onNavigateToStressHistory = onNavigateToStressHistory,
                    onNavigateToAddSleep = onNavigateToAddSleep,
                    onNavigateToAddStress = onNavigateToAddStress,
                    onNavigateToSelfJournalHistory = onNavigateToSelfJournalHistory,
                    onNavigateToAddJournaling = onNavigateToAddJournaling,
                    onError = ::onError,
                )
            }
            composable<Destination.App.DashBoard.Journaling> {
                JournalingScreen(
                    onNavigateToEditJournaling = onNavigateToEditJournaling,
                    onNavigateToAllJournals = onNavigateToSelfJournalHistory
                )
            }
        }
    }
}
