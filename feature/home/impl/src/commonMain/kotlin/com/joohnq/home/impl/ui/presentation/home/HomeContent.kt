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
import com.joohnq.home.impl.ui.components.FreudScoreMetric
import com.joohnq.home.impl.ui.components.HomeTopBar
import com.joohnq.home.impl.ui.components.MoodMetric
import com.joohnq.home.impl.ui.components.SelfJournalingMetric
import com.joohnq.home.impl.ui.presentation.home.event.HomeEvent
import com.joohnq.home.impl.ui.presentation.viewmodel.DashboardContract
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.components.view.LoadingView
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.self_journaling
import com.joohnq.shared_resources.sleep
import com.joohnq.shared_resources.stress
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.sleep_quality.impl.ui.component.SleepQualityMetric
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.impl.ui.component.StressLevelMetric
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.mapper.foldComposable

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
                    user = user
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.freud_score
                )
                FreudScoreMetric(
                    freudScore = state.freudScore,
                    onClick = { onEvent(HomeEvent.OnNavigateToFreudScore) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.mood,
                    onSeeMore = { onEvent(HomeEvent.OnNavigateToMood) }
                )
                MoodMetric(
                    records = moodRecords,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddMood) },
                    onClick = { onEvent(HomeEvent.OnNavigateToMood) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.sleep,
                    onSeeMore = { onEvent(HomeEvent.OnNavigateToSleepQuality) }
                )
                SleepQualityMetric(
                    records = sleepQualities,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddSleep) },
                    onClick = { onEvent(HomeEvent.OnNavigateToSleepQuality) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.stress,
                    onSeeMore = { onEvent(HomeEvent.OnNavigateToStressLevel) }
                )
                StressLevelMetric(
                    records = stressLevels,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddStressLevel) },
                    onClick = { onEvent(HomeEvent.OnNavigateToStressLevel) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.self_journaling,
                    onSeeMore = { onEvent(HomeEvent.OnNavigateToSelfJournal) }
                )
                SelfJournalingMetric(
                    records = selfJournals,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddJournaling) }
                )
                VerticalSpacer(padding.calculateBottomPadding() + 10.dp)
            }
        }
    )
}
