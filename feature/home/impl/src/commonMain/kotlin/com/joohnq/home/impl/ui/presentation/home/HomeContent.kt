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
import com.joohnq.api.entity.User
import com.joohnq.home.impl.ui.components.HomeTopBar
import com.joohnq.home.impl.ui.components.MoodMetric
import com.joohnq.home.impl.ui.components.SelfJournalMetric
import com.joohnq.home.impl.ui.presentation.home.event.HomeEvent
import com.joohnq.home.impl.ui.presentation.dashboard.DashboardContract
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.view.LoadingView
import com.joohnq.sleep_quality.impl.ui.component.SleepQualityMetric
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.impl.ui.component.StressLevelMetric
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.mapper.UiStateMapper.foldComposable

@Composable
fun HomeContent(
    state: DashboardContract.State,
    padding: PaddingValues = PaddingValues(0.dp),
    onEvent: (HomeEvent) -> Unit = {},
) {
    listOf(
        state.moodRecords,
        state.user,
        state.stressLevelRecords,
        state.selfJournalRecords,
        state.sleepQualityRecords
    ).foldComposable(
        onLoading = { LoadingView() },
        onSuccess = {
            moodRecords: List<MoodRecordResource>,
            user: User,
            stressLevels: List<StressLevelRecordResource>,
            selfJournals: List<SelfJournalRecordResource>,
            sleepQualities: List<SleepQualityRecordResource>,
            ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
            ) {
                HomeTopBar(
                    modifier =
                        Modifier
                            .padding(top = padding.calculateTopPadding()),
                    user = user,
                    freudScore = state.freudScore?.score ?: 0,
                    onNavigateToFreudScore = { onEvent(HomeEvent.OnNavigateToFreudScore) }
                )
//                FreudScoreMetric(
//                    freudScore = state.freudScore,
//                    onClick = { onEvent(HomeEvent.OnNavigateToFreudScore) }
//                )
                MoodMetric(
                    records = moodRecords,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddMood) },
                    onClick = { onEvent(HomeEvent.OnNavigateToMood) },
                )
                SleepQualityMetric(
                    records = sleepQualities,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddSleep) },
                    onClick = { onEvent(HomeEvent.OnNavigateToSleepQuality) }
                )
                StressLevelMetric(
                    records = stressLevels,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddStressLevel) },
                    onClick = { onEvent(HomeEvent.OnNavigateToStressLevel) }
                )
                SelfJournalMetric(
                    records = selfJournals,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddJournaling) },
                    onClick = { onEvent(HomeEvent.OnNavigateToSelfJournal) }
                )
                VerticalSpacer(padding.calculateBottomPadding() + 10.dp)
            }
        }
    )
}