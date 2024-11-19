package com.joohnq.moodapp.view.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.helper.DatetimeManager
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.HomeTopBar
import com.joohnq.moodapp.view.components.MentalHealthMetrics
import com.joohnq.moodapp.view.components.MindfulTracker
import com.joohnq.moodapp.view.components.Title
import com.joohnq.moodapp.view.routes.onNavigateToFreudScore
import com.joohnq.moodapp.view.routes.onNavigateToHealthJournal
import com.joohnq.moodapp.view.routes.onNavigateToMood
import com.joohnq.moodapp.view.routes.onNavigateToSleepQuality
import com.joohnq.moodapp.view.routes.onNavigateToStressLevel
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
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
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mental_health_metrics
import moodapp.composeapp.generated.resources.mindful_tracker

@Composable
fun HomeScreenUi(
    today: String = "",
    padding: PaddingValues = PaddingValues(0.dp),
    userName: String,
    statsRecord: StatsRecord,
    sleepQuality: SleepQuality,
    stressLevel: StressLevel,
    freudScore: FreudScore,
    moodTracker: List<Mood>,
    healthJournal: Map<String, List<StatsRecord>?>,
    onAction: (HomeAction) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .padding(bottom = padding.calculateBottomPadding())
    ) {
        HomeTopBar(
            modifier = Modifier.padding(
                top = padding.calculateTopPadding(),
            ),
            userName = userName,
            date = today
        )
        Title(Res.string.mental_health_metrics)
        MentalHealthMetrics(
            freudScore = freudScore,
            statsRecord = statsRecord,
            healthJournal = healthJournal,
            onAction = onAction
        )
        Title(Res.string.mindful_tracker)
        MindfulTracker(
            sleepQuality = sleepQuality,
            stressLevel = stressLevel,
            moodTracker = moodTracker,
            onAction = onAction
        )
    }
}

@Composable
fun HomeScreen(
    snackBarHostState: SnackbarHostState,
    padding: PaddingValues,
    navigation: NavHostController,
    statsViewModel: StatsViewModel = sharedViewModel(),
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    stressLevelViewModel: StressLevelViewModel = sharedViewModel(),
    healthJournalViewModel: HealthJournalViewModel = sharedViewModel(),
    userViewModel: UserViewModel = sharedViewModel(),
) {
    val scope = rememberCoroutineScope()
    val today = DatetimeManager.formatDate()
    val userState by userViewModel.userState.collectAsState()
    val statsState by statsViewModel.statsState.collectAsState()
    val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
    val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
    val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()

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

    if (
        UiState.allIsSuccess(
            statsState.statsRecords,
            userState.user,
            stressLevelState.stressLevelRecords,
            sleepQualityState.sleepQualityRecords,
            sleepQualityState.sleepQualityRecords
        )
    )
        HomeScreenUi(
            today = today,
            padding = padding,
            userName = userState.user.getValue().name,
            statsRecord = statsState.statsRecords.getValue().last(),
            moodTracker = statsState.statsRecords.getValue().reversed().take(3).map { it.mood },
            freudScore = statsState.freudScore,
            healthJournal = statsState.healthJournal,
            sleepQuality = sleepQualityState.sleepQualityRecords.getValue().last().sleepQuality,
            stressLevel = stressLevelState.stressLevelRecords.getValue().last().stressLevel,
            onAction = { action ->
                when (action) {
                    HomeAction.OnNavigateToFreudScore ->
                        navigation.onNavigateToFreudScore()

                    HomeAction.OnNavigateToMood ->
                        navigation.onNavigateToMood()

                    HomeAction.OnNavigateToHealthJournal ->
                        navigation.onNavigateToHealthJournal()

                    HomeAction.OnNavigateToMindfulJournal -> {}

                    HomeAction.OnNavigateToSleepQuality ->
                        navigation.onNavigateToSleepQuality()

                    HomeAction.OnNavigateToStressLevel ->
                        navigation.onNavigateToStressLevel()
                }
            }
        )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenUi(
        userName = "Henrique",
        statsRecord = StatsRecord.init().copy(
            mood = Mood.Overjoyed,
        ),
        freudScore = FreudScore.fromScore(80),
        moodTracker = listOf(
            Mood.Happy, Mood.Overjoyed
        ),
        sleepQuality = SleepQuality.Excellent,
        stressLevel = StressLevel.One,
        healthJournal = mapOf(
            "123" to listOf(StatsRecord.init())
        )
    )
}

@Preview
@Composable
fun HomeScreenPreview2() {
    HomeScreenUi(
        userName = "Henrique",
        statsRecord = StatsRecord.init().copy(
            mood = Mood.Happy
        ),
        freudScore = FreudScore.fromScore(60),
        moodTracker = listOf(
            Mood.Happy, Mood.Overjoyed
        ),
        sleepQuality = SleepQuality.Good,
        stressLevel = StressLevel.Two,
        healthJournal = mapOf(
            "123" to listOf(StatsRecord.init())
        )
    )
}

@Preview
@Composable
fun HomeScreenPreview3() {
    HomeScreenUi(
        userName = "Henrique",
        statsRecord = StatsRecord.init().copy(
            mood = Mood.Neutral,
        ),
        freudScore = FreudScore.fromScore(40),
        moodTracker = listOf(
            Mood.Happy, Mood.Overjoyed
        ),
        sleepQuality = SleepQuality.Fair,
        stressLevel = StressLevel.Three,
        healthJournal = mapOf(
            "123" to listOf(StatsRecord.init())
        )
    )
}

@Preview
@Composable
fun HomeScreenPreview4() {
    HomeScreenUi(
        userName = "Henrique",
        statsRecord = StatsRecord.init().copy(
            mood = Mood.Sad,
        ),
        freudScore = FreudScore.fromScore(20),
        moodTracker = listOf(
            Mood.Neutral, Mood.Sad, Mood.Neutral
        ),
        sleepQuality = SleepQuality.Poor,
        stressLevel = StressLevel.Four,
        healthJournal = mapOf(
            "123" to listOf(StatsRecord.init())
        )
    )
}

@Preview
@Composable
fun HomeScreenPreview5() {
    HomeScreenUi(
        userName = "Henrique",
        statsRecord = StatsRecord.init().copy(
            mood = Mood.Depressed,
        ),
        freudScore = FreudScore.fromScore(0),
        moodTracker = listOf(
            Mood.Happy, Mood.Overjoyed
        ),
        sleepQuality = SleepQuality.Worst,
        stressLevel = StressLevel.Five,
        healthJournal = mapOf(
            "123" to listOf(StatsRecord.init())
        )
    )
}
