package com.joohnq.moodapp.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.ui.components.HomeTopBar
import com.joohnq.moodapp.ui.components.MentalHealthMetrics
import com.joohnq.moodapp.ui.components.MindfulTracker
import com.joohnq.moodapp.ui.components.Title
import com.joohnq.moodapp.ui.presentation.home.state.HomeState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.ui.theme.Colors
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mental_health_metrics
import moodapp.composeapp.generated.resources.mindful_tracker

@Composable
fun HomeUI(
    state: HomeState,
) {
    val (today, userName, statsRecord, sleepQuality, stressLevel, freudScore, healthJournal, onEvent) = state

    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) {
        val padding = PaddingValues(
            top = it.calculateTopPadding(),
            bottom = it.calculateBottomPadding() + 80.dp,
            start = it.calculateStartPadding(LayoutDirection.Ltr),
            end = it.calculateEndPadding(LayoutDirection.Rtl)
        )
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                .padding(bottom = padding.calculateBottomPadding())
        ) {
            HomeTopBar(
                modifier = Modifier.padding(
                    top = padding.calculateTopPadding(),
                ),
                userName = userName.getValue().name,
                date = today
            )
            Title(Res.string.mental_health_metrics)
            MentalHealthMetrics(
                freudScore = freudScore,
                statsRecord = statsRecord.getValue().first(),
                healthJournal = healthJournal.getValue(),
                onEvent = onEvent
            )
            Title(Res.string.mindful_tracker)
            MindfulTracker(
                sleepQuality = sleepQuality.getValue().last().sleepQuality,
                stressLevel = stressLevel.getValue().last().stressLevel,
                moodTracker = statsRecord.getValue().take(3).reversed().map { it.mood },
                onAction = onEvent
            )
        }
    }
}