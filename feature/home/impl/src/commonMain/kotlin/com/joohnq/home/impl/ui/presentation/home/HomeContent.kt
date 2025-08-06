package com.joohnq.home.impl.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.home.impl.ui.components.HomeTopBar
import com.joohnq.home.impl.ui.components.MoodMetric
import com.joohnq.home.impl.ui.components.SelfJournalMetric
import com.joohnq.home.impl.ui.presentation.dashboard.DashboardContract
import com.joohnq.home.impl.ui.presentation.home.event.HomeEvent
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.view.LoadingView
import com.joohnq.sleep_quality.impl.ui.component.SleepQualityMetric
import com.joohnq.stress_level.impl.ui.component.StressLevelMetric

@Composable
fun HomeContent(
    state: DashboardContract.State,
    padding: PaddingValues = PaddingValues(0.dp),
    onEvent: (HomeEvent) -> Unit = {},
) {
    when {
        state.isLoading -> LoadingView()
        state.isError != null -> Unit
        else ->
            SuccessView(
                padding = padding,
                state = state,
                onEvent = onEvent
            )
    }
}

@Composable
private fun SuccessView(
    padding: PaddingValues = PaddingValues(0.dp),
    state: DashboardContract.State,
    onEvent: (HomeEvent) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
    ) {
        state.user?.let { user ->
            HomeTopBar(
                modifier =
                    Modifier
                        .padding(top = padding.calculateTopPadding()),
                user = user,
                freudScore = state.freudScore?.score ?: 0,
                onNavigateToFreudScore = { onEvent(HomeEvent.OnNavigateToFreudScore) }
            )
        }
        MoodMetric(
            records = state.moodItems,
            onCreate = { onEvent(HomeEvent.OnNavigateToAddMood) },
            onClick = { onEvent(HomeEvent.OnNavigateToMood) }
        )
        SleepQualityMetric(
            records = state.sleepQualityItems,
            onCreate = { onEvent(HomeEvent.OnNavigateToAddSleep) },
            onClick = { onEvent(HomeEvent.OnNavigateToSleepQuality) }
        )
        StressLevelMetric(
            records = state.stressLevelItems,
            onCreate = { onEvent(HomeEvent.OnNavigateToAddStressLevel) },
            onClick = { onEvent(HomeEvent.OnNavigateToStressLevel) }
        )
        SelfJournalMetric(
            records = state.selfJournalItems,
            onCreate = { onEvent(HomeEvent.OnNavigateToAddJournaling) },
            onClick = { onEvent(HomeEvent.OnNavigateToSelfJournal) }
        )
        VerticalSpacer(padding.calculateBottomPadding() + 10.dp)
    }
}
