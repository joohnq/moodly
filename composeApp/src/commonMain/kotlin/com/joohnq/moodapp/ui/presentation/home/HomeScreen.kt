package com.joohnq.moodapp.ui.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.freud_score.FreudScoreScreen
import com.joohnq.moodapp.ui.presentation.health_journal.HealthJournalScreen
import com.joohnq.moodapp.ui.presentation.home.event.HomeEvent
import com.joohnq.moodapp.ui.presentation.home.state.HomeState
import com.joohnq.moodapp.ui.presentation.mood.MoodScreen
import com.joohnq.moodapp.ui.presentation.sleep_quality.SleepQualityScreen
import com.joohnq.moodapp.ui.presentation.stress_level.StressLevelScreen
import com.joohnq.moodapp.ui.state.UiState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.util.helper.DatetimeManager
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel
import com.joohnq.moodapp.viewmodel.SleepQualityIntent
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import com.joohnq.moodapp.viewmodel.StatsIntent
import com.joohnq.moodapp.viewmodel.StatsViewModel
import com.joohnq.moodapp.viewmodel.StressLevelIntent
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import com.joohnq.moodapp.viewmodel.UserIntent
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class HomeScreen : CustomScreen<HomeState>() {
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
        val userState by userViewModel.userState.collectAsState()
        val statsState by statsViewModel.statsState.collectAsState()
        val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
        val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
        val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()

        fun onEvent(event: HomeEvent) =
            when (event) {
                HomeEvent.OnNavigateToFreudScore ->
                    onNavigate(FreudScoreScreen(), false)

                HomeEvent.OnNavigateToMood ->
                    onNavigate(MoodScreen(), false)

                HomeEvent.OnNavigateToHealthJournal ->
                    onNavigate(HealthJournalScreen(), false)

                HomeEvent.OnNavigateToMindfulJournal -> {}

                HomeEvent.OnNavigateToSleepQuality ->
                    onNavigate(SleepQualityScreen(), false)

                HomeEvent.OnNavigateToStressLevel ->
                    onNavigate(StressLevelScreen(), false)
            }

        SideEffect {
            statsViewModel.onAction(StatsIntent.GetStatsRecord)
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

//        if (
//            UiState.allIsSuccess(
//                statsState.statsRecords,
//                userState.user,
//                stressLevelState.stressLevelRecords,
//                sleepQualityState.sleepQualityRecords,
//                sleepQualityState.sleepQualityRecords
//            )
//        )
        return HomeState(
            today = today,
            padding = PaddingValues(),
            userName = userState.user.getValue().name,
            statsRecord = statsState.statsRecords.getValue().first(),
            moodTracker = statsState.statsRecords.getValue().take(3).reversed().map { it.mood },
            freudScore = statsState.freudScore,
            healthJournal = healthJournalState.healthJournalRecords.getValue(),
            sleepQuality = sleepQualityState.sleepQualityRecords.getValue().last().sleepQuality,
            stressLevel = stressLevelState.stressLevelRecords.getValue().last().stressLevel,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: HomeState) = HomeUI(state)
}
