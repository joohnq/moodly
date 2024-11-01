package com.joohnq.moodapp.view.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.view.OSType
import com.joohnq.moodapp.view.ScreenDimensions
import com.joohnq.moodapp.view.components.HomeTopBar
import com.joohnq.moodapp.view.components.MentalHealthMetrics
import com.joohnq.moodapp.view.components.MindfulTracker
import com.joohnq.moodapp.view.components.Title
import com.joohnq.moodapp.view.routes.onNavigateToFreudScore
import com.joohnq.moodapp.view.routes.onNavigateToHealthJournal
import com.joohnq.moodapp.view.routes.onNavigateToMood
import com.joohnq.moodapp.view.screens.home.action.HomeAction
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.view.state.UiState.Companion.getValueOrNull
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreenUi(
    today: String = "",
    padding: PaddingValues = PaddingValues(0.dp),
    userName: String,
    statsRecord: StatsRecord,
    freudScore: FreudScore,
    moodTracker: List<Mood>,
    healthJournal: List<StatsRecord?>,
    onAction: (HomeAction) -> Unit = {},
) {
    val screenDimensions: ScreenDimensions = koinInject()
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .padding(padding).windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        HomeTopBar(
            modifier = Modifier,
            userName = userName,
            date = today
        )
        Title("Mental Health Metrics")
        MentalHealthMetrics(
            freudScore = freudScore,
            mood = statsRecord.mood,
            healthJournal = healthJournal,
            onAction = onAction
        )
        Title("Mindful Tracker")
        MindfulTracker(
            sleepQuality = statsRecord.sleepQuality,
            stressLevel = statsRecord.stressLevel,
            moodTracker = moodTracker
        )
    }
}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    padding: PaddingValues,
    navController: NavController = rememberNavController(),
    moodsViewModel: MoodsViewModel = koinNavViewModel(),
    userViewModel: UserViewModel = koinNavViewModel()
) {
    val today = DatetimeHelper.getDateTime()
    val user = userViewModel.user.value.getValueOrNull()!!
    val statsRecords =
        (moodsViewModel.statsRecords.collectAsState().value as UiState.Success<List<StatsRecord>>).data
    val freudScore by moodsViewModel.freudScore.collectAsState()
    val healthJournal by moodsViewModel.healthJournal.collectAsState()

    HomeScreenUi(
        today = today,
        padding = padding,
        userName = user.name,
        statsRecord = statsRecords.first(),
        moodTracker = statsRecords.take(3).map { it.mood },
        freudScore = freudScore,
        healthJournal = healthJournal,
        onAction = {
            when (it) {
                is HomeAction.OnNavigateToFreudScore -> navController.onNavigateToFreudScore()
                is HomeAction.OnNavigateToMood -> navController.onNavigateToMood(statsRecords.first())
                is HomeAction.OnNavigateToHealthJournal -> navController.onNavigateToHealthJournal()
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
            sleepQuality = SleepQuality.Excellent,
            stressLevel = StressLevel.One,
            mood = Mood.Overjoyed,
        ),
        freudScore = FreudScore.fromScore(80),
        moodTracker = listOf(

        ),
        healthJournal = listOf(
            StatsRecord.init()
        ),
    )
}

@Preview
@Composable
fun HomeScreenPreview2() {
    HomeScreenUi(
        userName = "Henrique",
        statsRecord = StatsRecord.init().copy(
            sleepQuality = SleepQuality.Good,
            stressLevel = StressLevel.Two,
            mood = Mood.Happy
        ),
        freudScore = FreudScore.fromScore(60),
        moodTracker = listOf(

        ),
        healthJournal = listOf(
            StatsRecord.init()
        )
    )
}

@Preview
@Composable
fun HomeScreenPreview3() {
    HomeScreenUi(
        userName = "Henrique",
        statsRecord = StatsRecord.init().copy(
            sleepQuality = SleepQuality.Fair,
            stressLevel = StressLevel.Three,
            mood = Mood.Neutral,
        ),
        freudScore = FreudScore.fromScore(40),
        moodTracker = listOf(

        ),
        healthJournal = listOf(
            StatsRecord.init()
        )
    )
}

@Preview
@Composable
fun HomeScreenPreview4() {
    HomeScreenUi(
        userName = "Henrique",
        statsRecord = StatsRecord.init().copy(
            sleepQuality = SleepQuality.Poor,
            stressLevel = StressLevel.Four,
            mood = Mood.Sad,
        ),
        freudScore = FreudScore.fromScore(20),
        moodTracker = listOf(

        ),
        healthJournal = listOf(
            StatsRecord.init()
        )
    )
}

@Preview
@Composable
fun HomeScreenPreview5() {
    HomeScreenUi(
        userName = "Henrique",
        statsRecord = StatsRecord.init().copy(
            sleepQuality = SleepQuality.Worst,
            stressLevel = StressLevel.Five,
            mood = Mood.Depressed,
        ),
        freudScore = FreudScore.fromScore(0),
        moodTracker = listOf(

        ),
        healthJournal = listOf(
            StatsRecord.init()
        )
    )
}

