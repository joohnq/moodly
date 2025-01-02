package com.joohnq.home.ui.presentation.home

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
import com.joohnq.home.ui.components.MindfulTracker
import com.joohnq.home.ui.presentation.home.state.HomeState
import com.joohnq.shared.ui.components.HomeTopBar
import com.joohnq.shared.ui.components.MentalHealthMetrics
import com.joohnq.shared.ui.components.Title
import com.joohnq.shared.ui.state.UiState
import com.joohnq.shared.ui.state.UiState.Companion.getValue
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.presentation.loading.LoadingUI
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.mental_health_metrics
import com.joohnq.shared.ui.mindful_tracker

@Composable
fun HomeUI(
    state: HomeState,
) {
    UiState.onFold(
        state.statsRecord,
        state.userName,
        state.stressLevel,
        state.healthJournal,
        state.sleepQuality,
        onLoading = { LoadingUI() },
        onAllSuccess = {
            Scaffold(
                containerColor = Colors.Brown10,
                modifier = Modifier.fillMaxSize()
            ) { pad ->
                val padding = PaddingValues(
                    top = pad.calculateTopPadding(),
                    bottom = pad.calculateBottomPadding() + 80.dp,
                    start = pad.calculateStartPadding(LayoutDirection.Ltr),
                    end = pad.calculateEndPadding(LayoutDirection.Rtl)
                )
                Column(
                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                        .padding(bottom = padding.calculateBottomPadding())
                ) {
                    HomeTopBar(
                        modifier = Modifier.padding(
                            top = padding.calculateTopPadding(),
                        ),
                        userName = state.userName.getValue().name,
                        date = state.today
                    )
                    Title(Res.string.mental_health_metrics)
                    MentalHealthMetrics(
                        freudScore = state.freudScore,
                        statsRecord = state.statsRecord.getValue().first(),
                        healthJournal = state.healthJournal.getValue(),
                        onEvent = state.onEvent
                    )
                    Title(Res.string.mindful_tracker)
                    MindfulTracker(
                        sleepQuality = state.sleepQuality.getValue().last().sleepQuality,
                        stressLevel = state.stressLevel.getValue().last().stressLevel,
                        moodTracker = state.statsRecord.getValue().take(3).reversed()
                            .map { statsRecord -> statsRecord.mood },
                        onAction = state.onEvent
                    )
                }
            }
        }
    )
}