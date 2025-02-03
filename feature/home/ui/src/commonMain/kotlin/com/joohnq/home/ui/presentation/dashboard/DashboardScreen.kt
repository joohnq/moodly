package com.joohnq.home.ui.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
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
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.components.takeIf
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.viewmodel.user.UserIntent
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

data class CentralAction(
    val title: StringResource,
    val icon: DrawableResource,
    val destination: Destination
)

@Composable
fun CentralButton(
    modifier: Modifier = Modifier,
    item: CentralAction,
    onClick: (Destination) -> Unit
) {
    Column(
        modifier = modifier.clickable(onClick = { onClick(item.destination) }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color = Colors.Brown10, shape = Dimens.Shape.Circle)
                .clip(Dimens.Shape.Circle),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = stringResource(item.title),
                tint = Colors.Brown60,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = stringResource(item.title),
            style = TextStyles.TextSmRegular(),
            color = Colors.Gray60
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DashboardCentral(padding: PaddingValues, onNavigate: (Destination) -> Unit) {
    val items =
        listOf<CentralAction>(
            CentralAction(
                title = Res.string.sleep,
                icon = Drawables.Icons.Sleep,
                destination = Destination.App.AddSleepQuality
            ),
            CentralAction(
                title = Res.string.stress_level,
                icon = Drawables.Icons.Outlined.Warning,
                destination = Destination.App.AddStressLevel
            ),
            CentralAction(
                title = Res.string.self_journaling,
                icon = Drawables.Icons.Outlined.BookOpen,
                destination = Destination.App.AddSelfJournaling
            ),
            CentralAction(
                title = Res.string.mood,
                icon = Drawables.Icons.Outlined.Mood,
                destination = Destination.App.AddStat
            )
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.Black48)
            .padding(bottom = padding.calculateBottomPadding() + 10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .paddingHorizontalMedium()
                .background(color = Colors.White, shape = Dimens.Shape.Large)
                .clip(Dimens.Shape.Large)
                .paddingAllSmall(),
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items.forEach { item ->
                CentralButton(
                    modifier = Modifier.weight(1f),
                    item = item,
                    onClick = onNavigate
                )
            }
        }
    }
}

@Composable
fun DashboardScreen(
    onNavigate: (Destination) -> Unit,
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
        NavHost(
            modifier = Modifier
                .takeIf(centralIsExpanded) {
                    Modifier.blur(
                        radius = 15.dp,
                    )
                },
            navController = navigator,
            startDestination = Destination.App.DashBoard.Home
        ) {
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

        if (centralIsExpanded)
            DashboardCentral(
                padding = padding,
                onNavigate = onNavigate
            )
    }
}
