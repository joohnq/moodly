package com.joohnq.home.impl.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.gratefulness.impl.ui.presentation.component.GratefulnessMetric
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
                state = state,
                padding = padding,
                onEvent = onEvent
            )
    }
}

@Composable
private fun SuccessView(
    state: DashboardContract.State,
    padding: PaddingValues,
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
                modifier = Modifier.statusBarsPadding(),
                user = user,
                freudScore = state.freudScore?.score ?: 0,
                onNavigateToFreudScore = { onEvent(HomeEvent.NavigateToFreudScore) }
            )
        }
        MoodMetric(
            items = state.moodItems,
            onCreate = { onEvent(HomeEvent.NavigateToAddMood) },
            onClick = { onEvent(HomeEvent.NavigateToMoodOverview) }
        )
        SleepQualityMetric(
            items = state.sleepQualityItems,
            onCreate = { onEvent(HomeEvent.NavigateToAddSleepQuality) },
            onClick = { onEvent(HomeEvent.NavigateToSleepQualityOverview) }
        )
        StressLevelMetric(
            items = state.stressLevelItems,
            onCreate = { onEvent(HomeEvent.NavigateToAddStressLevel) },
            onClick = { onEvent(HomeEvent.NavigateToStressLevelOverview) }
        )
        SelfJournalMetric(
            items = state.selfJournalItems,
            onCreate = { onEvent(HomeEvent.NavigateToAddSelfJournal) },
            onClick = { onEvent(HomeEvent.NavigateToSelfJournalOverview) }
        )
        GratefulnessMetric(
            resource = state.gratefulnessToday,
            onCreate = { onEvent(HomeEvent.OnNavigateToAddGratefulness) },
            onClick = { onEvent(HomeEvent.NavigateToGratefulnessOverview) }
        )
        VerticalSpacer(padding.calculateBottomPadding())
    }
}
