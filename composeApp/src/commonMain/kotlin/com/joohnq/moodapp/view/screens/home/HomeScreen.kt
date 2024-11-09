package com.joohnq.moodapp.view.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.ui.unit.dp
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
    healthJournal: List<StatsRecord?>,
    onAction: (HomeAction) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .padding(padding).windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        HomeTopBar(
            modifier = Modifier,
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
    statsViewModel: StatsViewModel = sharedViewModel(),
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    stressLevelViewModel: StressLevelViewModel = sharedViewModel(),
    userViewModel: UserViewModel = sharedViewModel(),
    onAction: (HomeAction) -> Unit,
) {
    val today = DatetimeHelper.getDateTime()
    val userState by userViewModel.userState.collectAsState()
    val moodsState by statsViewModel.statsState.collectAsState()
    val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
    val stressLevel by stressLevelViewModel.stressLevelState.collectAsState()
    val statsRecord = moodsState.statsRecords.getValue()

    HomeScreenUi(
        today = today,
        padding = padding,
        userName = userState.user.getValue().name,
        statsRecord = statsRecord.first(),
        moodTracker = statsRecord.take(3).map { it.mood },
        freudScore = moodsState.freudScore,
        healthJournal = moodsState.healthJournal,
        sleepQuality = sleepQualityState.items.getValue().first().sleepQuality,
        stressLevel = stressLevel.items.getValue().first().stressLevel,
        onAction = onAction
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
            mood = Mood.Happy
        ),
        freudScore = FreudScore.fromScore(60),
        moodTracker = listOf(
            Mood.Happy, Mood.Overjoyed
        ),
        sleepQuality = SleepQuality.Good,
        stressLevel = StressLevel.Two,
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
            mood = Mood.Neutral,
        ),
        freudScore = FreudScore.fromScore(40),
        moodTracker = listOf(
            Mood.Happy, Mood.Overjoyed
        ),
        sleepQuality = SleepQuality.Fair,
        stressLevel = StressLevel.Three,
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
            mood = Mood.Sad,
        ),
        freudScore = FreudScore.fromScore(20),
        moodTracker = listOf(
            Mood.Neutral, Mood.Sad, Mood.Neutral
        ),
        sleepQuality = SleepQuality.Poor,
        stressLevel = StressLevel.Four,
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
            mood = Mood.Depressed,
        ),
        freudScore = FreudScore.fromScore(0),
        moodTracker = listOf(
            Mood.Happy, Mood.Overjoyed
        ),
        sleepQuality = SleepQuality.Worst,
        stressLevel = StressLevel.Five,
        healthJournal = listOf(
            StatsRecord.init()
        )
    )
}

