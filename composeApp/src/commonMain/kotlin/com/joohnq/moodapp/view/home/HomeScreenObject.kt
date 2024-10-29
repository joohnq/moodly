package com.joohnq.moodapp.view.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.DatetimeHelper
import com.joohnq.moodapp.MoodsManager
import com.joohnq.moodapp.model.entities.MentalMetric
import com.joohnq.moodapp.model.entities.StatsRecord
import com.joohnq.moodapp.model.entities.User
import com.joohnq.moodapp.model.entities.mockUser
import com.joohnq.moodapp.view.components.FreudScoreComponent
import com.joohnq.moodapp.view.components.HealthJournalComponent
import com.joohnq.moodapp.view.components.HomeTitle
import com.joohnq.moodapp.view.components.HomeTopBar
import com.joohnq.moodapp.view.components.MoodComponent
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.state.UiState.Companion.getValueOrNull
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun HomeScreenUi(user: User?, monthlyMoods: List<StatsRecord?>?) {
    if (user == null || monthlyMoods == null) return

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
                Spacer(modifier = Modifier.height(32.dp))
                HomeTitle("Mental Health Metrics")
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(healthMetricsItems, key = { it.id }) { metric ->
                        Column(
                            modifier = Modifier.background(
                                color = metric.backgroundColor,
                                shape = RoundedCornerShape(20.dp)
                            ).fillMaxSize().padding(20.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(metric.icon),
                                    contentDescription = metric.title,
                                    tint = Colors.White,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = metric.title,
                                    style = TextStyles.HomeMetricTitle()
                                )
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                            metric.content(
                                Modifier.heightIn(min = 130.dp, max = 140.dp)
                                    .widthIn(min = 130.dp, max = 140.dp).fillMaxSize()
                            )
                        }
                    }
                }
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

    HomeScreenUi(user.getValueOrNull(), monthlyMoods.getValueOrNull())
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenUi(
        user = mockUser(),
        monthlyMoods = listOf()
    )
}

