package com.joohnq.moodapp.view.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.DatetimeHelper
import com.joohnq.moodapp.MoodsManager
import com.joohnq.moodapp.model.entities.FreudScore
import com.joohnq.moodapp.model.entities.MentalMetric
import com.joohnq.moodapp.model.entities.Mood
import com.joohnq.moodapp.model.entities.SleepQuality
import com.joohnq.moodapp.model.entities.StatsRecord
import com.joohnq.moodapp.model.entities.StressLevel
import com.joohnq.moodapp.view.components.FreudScoreComponent
import com.joohnq.moodapp.view.components.HealthJournalComponent
import com.joohnq.moodapp.view.components.HomeTitle
import com.joohnq.moodapp.view.components.HomeTopBar
import com.joohnq.moodapp.view.components.MentalHealthMetrics
import com.joohnq.moodapp.view.components.MindfulTrackerCardColumn
import com.joohnq.moodapp.view.components.MindfulTrackerCardRow
import com.joohnq.moodapp.view.components.MoodComponent
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.state.UiState.Companion.getValueOrNull
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mindful_journal
import moodapp.composeapp.generated.resources.stress_level
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreenUi(
    padding: PaddingValues = PaddingValues(0.dp),
    userName: String,
    sleepQuality: SleepQuality,
    stressLevel: StressLevel,
    freudScore: FreudScore,
    moods: List<StatsRecord?>,
    healthJournal: List<StatsRecord?>,
) {
    val today = remember { DatetimeHelper.getDateTime() }

    val healthMetricsItems = remember {
        listOf(
            MentalMetric.FreudScore { modifier ->
                FreudScoreComponent(
                    modifier,
                    freudScore
                )
            },
            MentalMetric.Mood { modifier ->
                MoodComponent(
                    modifier,
                    moods
                )
            },
            MentalMetric.HealthJournal { modifier ->
                HealthJournalComponent(
                    modifier,
                    healthJournal
                )
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(padding)
    ) {
        HomeTopBar(
            modifier = Modifier,
            userName = userName,
            date = today
        )
        HomeTitle("Mental Health Metrics")
        MentalHealthMetrics(healthMetricsItems)
        HomeTitle("Mindful Tracker")
        MindfulTrackerCardRow(
            icon = Drawables.Icons.HospitalBed,
            color = sleepQuality.color,
            backgroundColor = sleepQuality.backgroundColor,
            title = sleepQuality.firstText,
            subtitle = sleepQuality.secondText,
            content = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(56.dp)
                ) {
                    CircularProgressIndicator(
                        progress = { sleepQuality.level * 0.2f },
                        modifier = Modifier.fillMaxSize(),
                        color = sleepQuality.color,
                        strokeWidth = 10.dp,
                        trackColor = sleepQuality.backgroundColor,
                        strokeCap = StrokeCap.Round
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = sleepQuality.level.toString(),
                            style = TextStyles.SleepQualityOption()
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        MindfulTrackerCardRow(
            icon = Drawables.Icons.DocumentHealth,
            color = Colors.Orange40,
            backgroundColor = Colors.Orange10,
            title = Res.string.mindful_journal,
            subtitle = Res.string.mindful_journal,
            content = { modifier ->
                LazyVerticalGrid(
                    modifier = modifier.wrapContentSize(unbounded = true).size(60.dp),
                    columns = GridCells.Fixed(4),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(16) { i ->
                        val background = when (i) {
                            5, 6, 7, 8, 9 -> Colors.Orange40
                            2, 3, 10, 11 -> Colors.Orange20
                            else -> Colors.Orange10
                        }

                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(
                                    color = background,
                                    shape = RoundedCornerShape(4.dp)
                                ),
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        MindfulTrackerCardColumn(
            icon = Drawables.Icons.Head,
            color = Colors.Yellow50,
            backgroundColor = Colors.Yellow10,
            title = Res.string.stress_level,
            subtitle = stressLevel.subtitle,
            content = {
                BoxWithConstraints {
                    val boxWidth = (maxWidth - 16.dp) / 5
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        for (i in 1..5) {
                            val isSelected = i <= stressLevel.level
                            Box(
                                modifier = Modifier.width(boxWidth).height(6.dp)
                                    .background(
                                        color = if (isSelected) Colors.Yellow50 else Colors.Brown20,
                                        shape = CircleShape
                                    )
                            )
                        }
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    padding: PaddingValues = PaddingValues(0.dp),
    navController: NavController = rememberNavController(),
    moodsViewModel: MoodsViewModel = koinNavViewModel(),
    userViewModel: UserViewModel = koinNavViewModel()
) {
    val user = userViewModel.user.value.getValueOrNull()!!
    val monthlyMoods = moodsViewModel.monthlyMoods.value.getValueOrNull()!!
    val recentMood by remember { mutableStateOf(monthlyMoods.first()) }
    val freudScore by remember { mutableStateOf(MoodsManager.getFreudScore(monthlyMoods)) }
    val mood by remember { mutableStateOf(MoodsManager.getMood(monthlyMoods)) }
    val healthJournal by remember { mutableStateOf(MoodsManager.getHealthJournal(monthlyMoods)) }

    HomeScreenUi(
        padding = padding,
        userName = user.name,
        sleepQuality = recentMood.sleepQuality,
        stressLevel = recentMood.stressLevel,
        freudScore,
        mood,
        healthJournal,
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenUi(
        userName = "Henrique",
        sleepQuality = SleepQuality.Worst,
        stressLevel = StressLevel.One,
        freudScore = FreudScore.AtRisk(60),
        moods = listOf(
            StatsRecord(
                mood = Mood.Sad,
                sleepQuality = SleepQuality.Worst,
                stressLevel = StressLevel.One,
                description = "",
                date = Clock.System.now()
                    .toLocalDateTime(TimeZone.currentSystemDefault()).date
            )
        ),
        healthJournal = listOf(
            StatsRecord(
                mood = Mood.Sad,
                sleepQuality = SleepQuality.Worst,
                stressLevel = StressLevel.One,
                description = "",
                date = Clock.System.now()
                    .toLocalDateTime(TimeZone.currentSystemDefault()).date
            )
        )
    )
}

