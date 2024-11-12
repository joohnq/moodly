package com.joohnq.moodapp.view.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.helper.DatetimeHelper
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
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import com.joohnq.moodapp.viewmodel.StatsViewModel
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
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
    padding: PaddingValues,
    navigation: NavHostController,
    statsViewModel: StatsViewModel = sharedViewModel(),
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    stressLevelViewModel: StressLevelViewModel = sharedViewModel(),
    userViewModel: UserViewModel = sharedViewModel(),
) {
    val today = DatetimeHelper.getDateTime()
    val user by userViewModel.user.collectAsState()
    val statsRecords by statsViewModel.statsRecords.collectAsState()
    val freudScore by statsViewModel.freudScore.collectAsState()
    val healthJournal by statsViewModel.healthJournal.collectAsState()
    val sleepQualityItems by sleepQualityViewModel.items.collectAsState()
    val stressLevelItems by stressLevelViewModel.items.collectAsState()
    var show by remember { mutableStateOf(false) }

    SideEffect {
        statsViewModel.getStats()
        userViewModel.getUser()
        stressLevelViewModel.getStressLevelRecords()
        sleepQualityViewModel.getSleepQualityRecords()
    }

    LaunchedEffect(
        statsRecords,
        user,
        stressLevelItems,
        sleepQualityItems
    ) {
        when (statsRecords is UiState.Success && stressLevelItems is UiState.Success && sleepQualityItems is UiState.Success && user is UiState.Success) {
            true -> show = true
            else -> Unit
        }
    }

    if (show)
        HomeScreenUi(
            today = today,
            padding = padding,
            userName = user.getValue().name,
            statsRecord = statsRecords.getValue().first(),
            moodTracker = statsRecords.getValue().take(3).map { it.mood }.reversed(),
            freudScore = freudScore,
            healthJournal = healthJournal,
            sleepQuality = sleepQualityItems.getValue().first().sleepQuality,
            stressLevel = stressLevelItems.getValue().first().stressLevel,
            onAction = { action ->
                when (action) {
                    is HomeAction.OnNavigateToFreudScore ->
                        navigation.onNavigateToFreudScore()

                    is HomeAction.OnNavigateToMood ->
                        navigation.onNavigateToMood(statsRecords.getValue().first())

                    is HomeAction.OnNavigateToHealthJournal ->
                        navigation.onNavigateToHealthJournal()

                    HomeAction.OnNavigateToMindfulJournal -> {}
                    HomeAction.OnNavigateToMoodTracker ->
                        navigation.onNavigateToMood(statsRecords.getValue().first())

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
