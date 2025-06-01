package com.joohnq.home.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.domain.mapper.handle
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.home.ui.components.FreudScoreMetric
import com.joohnq.home.ui.components.HomeTopBar
import com.joohnq.home.ui.components.MoodMetric
import com.joohnq.home.ui.components.SelfJournalingMetric
import com.joohnq.home.ui.presentation.dashboard.viewmodel.DashboardContract
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.self_journaling
import com.joohnq.shared_resources.sleep
import com.joohnq.shared_resources.stress
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.sleep_quality.ui.component.SleepQualityMetric
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.ui.component.StressLevelMetric
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeUI(
    padding: PaddingValues,
    user: User,
    state: DashboardContract.State,
    onEvent: (DashboardContract.Event) -> Unit = {},
) {
    state.status.handle(
        onLoading = { LoadingUI() },
        onSuccess = {
            val (_, moodRecords, selfJournals, sleepQualities, stressLevels, _) = state
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                HomeTopBar(
                    modifier = Modifier
                        .padding(top = padding.calculateTopPadding()),
                    user = user,
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.freud_score,
                )
                FreudScoreMetric(
                    freudScore = state.freudScore,
                    onClick = { onEvent(DashboardContract.Event.NavigateToFreudScore) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.mood,
                    onSeeMore = { onEvent(DashboardContract.Event.NavigateToMood) }
                )
                MoodMetric(
                    records = moodRecords,
                    containerColor = Colors.White,
                    onCreate = { onEvent(DashboardContract.Event.NavigateToAddMood) },
                    onClick = { onEvent(DashboardContract.Event.NavigateToMood) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.sleep,
                    onSeeMore = { onEvent(DashboardContract.Event.NavigateToSleepQuality) }
                )
                SleepQualityMetric(
                    records = sleepQualities,
                    containerColor = Colors.White,
                    onCreate = { onEvent(DashboardContract.Event.NavigateToAddSleep) },
                    onClick = { onEvent(DashboardContract.Event.NavigateToSleepQuality) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.stress,
                    onSeeMore = { onEvent(DashboardContract.Event.NavigateToStressLevel) }
                )
                StressLevelMetric(
                    records = stressLevels,
                    containerColor = Colors.White,
                    onCreate = { onEvent(DashboardContract.Event.NavigateToAddStressLevel) },
                    onClick = { onEvent(DashboardContract.Event.NavigateToStressLevel) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.self_journaling,
                    onSeeMore = { onEvent(DashboardContract.Event.NavigateToSelfJournal) }
                )
                SelfJournalingMetric(
                    records = selfJournals,
                    containerColor = Colors.White,
                    onCreate = { onEvent(DashboardContract.Event.NavigateToAddJournaling) },
                )
                VerticalSpacer(padding.calculateBottomPadding() + 10.dp)
            }
        }
    )
}

@Preview
@Composable
fun HomeUIPreview() {
    HomeUI(
        padding = PaddingValues(0.dp),
        user = User(),
        state = DashboardContract.State(
            freudScore = FreudScoreResource.Healthy(80),
            moodRecords = listOf(
                MoodRecordResource()
            ),
            stressLevelRecords = listOf(
                StressLevelRecordResource()
            ),
            selfJournalRecords = listOf(
                SelfJournalRecordResource()
            ),
            sleepQualityRecords = listOf(
                SleepQualityRecordResource()
            ),
            status = UiState.Success(Unit)
        ),
    )
}
