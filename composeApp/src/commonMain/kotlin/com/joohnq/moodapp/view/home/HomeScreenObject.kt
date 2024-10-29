package com.joohnq.moodapp.view.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.DatetimeHelper
import com.joohnq.moodapp.MoodsManager
import com.joohnq.moodapp.model.entities.MentalMetric
import com.joohnq.moodapp.model.entities.SleepQuality
import com.joohnq.moodapp.model.entities.StatsRecord
import com.joohnq.moodapp.model.entities.StressLevel
import com.joohnq.moodapp.model.entities.User
import com.joohnq.moodapp.model.entities.mockUser
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
import com.joohnq.moodapp.view.state.UiState.Companion.onSuccess
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mindful_journal
import moodapp.composeapp.generated.resources.stress_level
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreenUi(
    user: User?,
    monthlyMoods: List<StatsRecord?>?,
    recentMood: StatsRecord?
) {
    if (user == null || monthlyMoods == null || recentMood == null) return

    val today = remember { DatetimeHelper.getDateTime() }
    val freudScore = remember { MoodsManager.getFreudScore(monthlyMoods) }
    val mood = remember { MoodsManager.getMood(monthlyMoods) }
    val healthJournal = remember { MoodsManager.getHealthJournal(monthlyMoods) }

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
                    mood
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

    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        val modifier =
            Modifier.padding(horizontal = padding.calculateStartPadding(LayoutDirection.Ltr))
                .padding(bottom = padding.calculateBottomPadding())
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            HomeTopBar(
                modifier = Modifier.padding(top = padding.calculateTopPadding()),
                user = user,
                date = today
            )
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                HomeTitle("Mental Health Metrics")
                MentalHealthMetrics(healthMetricsItems)
                HomeTitle("Mindful Tracker")
                if (recentMood.sleepQuality != null)
                    MindfulTrackerCardRow(
                        icon = Drawables.Icons.HospitalBed,
                        color = recentMood.sleepQuality.color,
                        backgroundColor = recentMood.sleepQuality.backgroundColor,
                        title = recentMood.sleepQuality.firstText,
                        subtitle = recentMood.sleepQuality.secondText,
                        content = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.size(56.dp)
                            ) {
                                CircularProgressIndicator(
                                    progress = { recentMood.sleepQuality.level * 0.2f },
                                    modifier = Modifier.fillMaxSize(),
                                    color = recentMood.sleepQuality.color,
                                    strokeWidth = 10.dp,
                                    trackColor = recentMood.sleepQuality.backgroundColor,
                                    strokeCap = StrokeCap.Round
                                )
                                Column(
                                    modifier = Modifier.padding(horizontal = 15.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = recentMood.sleepQuality.level.toString(),
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
                if (recentMood.stressLevel != null)
                    MindfulTrackerCardColumn(
                        icon = Drawables.Icons.Head,
                        color = Colors.Yellow50,
                        backgroundColor = Colors.Yellow10,
                        title = Res.string.stress_level,
                        subtitle = recentMood.stressLevel.subtitle,
                        content = {
                            BoxWithConstraints {
                                val boxWidth = (maxWidth - 16.dp) / 5
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    for (i in 1..5) {
                                        val isSelected = i <= recentMood.stressLevel.level
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
            }
        }
    }
}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    navigation: NavController = rememberNavController(),
    moodsViewModel: MoodsViewModel = koinNavViewModel(),
    userViewModel: UserViewModel = koinNavViewModel()
) {
    val user by userViewModel.user.collectAsState()
    val monthlyMoods by moodsViewModel.monthlyMoods.collectAsState()
    var recentMood by remember { mutableStateOf<StatsRecord?>(null) }

    LaunchedEffect(monthlyMoods) {
        monthlyMoods.onSuccess {
            val mood = it[0]
            recentMood = mood
        }
    }

    HomeScreenUi(user.getValueOrNull(), monthlyMoods.getValueOrNull(), recentMood)
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenUi(
        user = mockUser(),
        monthlyMoods = listOf(),
        recentMood = StatsRecord(
            sleepQuality = SleepQuality.Worst,
            stressLevel = StressLevel.One
        )
    )
}

