package com.joohnq.home.ui.presentation.home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.home.ui.presentation.home.state.HomeState
import com.joohnq.mood.CustomTab
import com.joohnq.mood.sharedViewModel
import com.joohnq.mood.state.UiState
import com.joohnq.mood.theme.Drawables
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.mood.util.helper.DatetimeManager
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.home
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.viewmodel.UserViewModelIntent
import com.joohnq.user.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

class HomeScreen : CustomTab<HomeState>() {
    @Composable
    override fun Screen(): HomeState {
        val snackBarHostState = remember { SnackbarHostState() }
        val statsViewModel: StatsViewModel = sharedViewModel()
        val userViewModel: UserViewModel = sharedViewModel()
        val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel()
        val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val today = DatetimeManager.formatDate()
        val userState by userViewModel.state.collectAsState()
        val statsState by statsViewModel.statsState.collectAsState()
        val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
        val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
        val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()

        fun onEvent(event: HomeEvent) =
            when (event) {
                HomeEvent.OnNavigateToFreudScore -> {}
//                    onNavigate(FreudScoreScreen())

                HomeEvent.OnNavigateToMood -> {}
//                    onNavigate(MoodScreen())

                HomeEvent.OnNavigateToHealthJournal -> {}
//                    onNavigate(HealthJournalScreen())

                HomeEvent.OnNavigateToMindfulJournal -> {}

                HomeEvent.OnNavigateToSleepQuality -> {}
//                    onNavigate(SleepQualityScreen())

                HomeEvent.OnNavigateToStressLevel -> {}
//                    onNavigate(StressLevelScreen())
            }

        SideEffect {
            statsViewModel.onAction(StatsIntent.GetStatsRecords)
            userViewModel.onAction(UserViewModelIntent.GetUser)
            stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)
            sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
            healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
        }

        LaunchedEffect(
            statsState.statsRecords,
            userState.user,
            stressLevelState.stressLevelRecords,
            sleepQualityState.sleepQualityRecords,
            healthJournalState.healthJournalRecords
        ) {
            UiState.onAnyError(
                statsState.statsRecords,
                userState.user,
                stressLevelState.stressLevelRecords,
                sleepQualityState.sleepQualityRecords,
                sleepQualityState.sleepQualityRecords
            ) {
                scope.launch { snackBarHostState.showSnackbar(it) }
            }
        }

        return HomeState(
            today = today,
            userName = userState.user,
            statsRecord = statsState.statsRecords,
            freudScore = statsState.freudScore,
            healthJournal = healthJournalState.healthJournalRecords,
            sleepQuality = sleepQualityState.sleepQualityRecords,
            stressLevel = stressLevelState.stressLevelRecords,
            onEvent = ::onEvent
        )
    }

    override val options: TabOptions
        @Composable
        get() =
            TabOptions(
                icon = painterResource(Drawables.Icons.Home),
                title = stringResource(Res.string.home),
                index = 0u
            )

    @Composable
    override fun UI(state: HomeState) = HomeUI(state = state)
}
