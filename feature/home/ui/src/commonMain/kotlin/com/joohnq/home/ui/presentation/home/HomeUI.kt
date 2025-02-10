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
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.domain.entity.User
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.home.ui.components.FreudScoreMetric
import com.joohnq.home.ui.components.HomeTopBar
import com.joohnq.home.ui.components.MoodMetric
import com.joohnq.home.ui.components.SelfJournalingMetric
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.home.ui.presentation.viewmodel.DashboardState
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
    state: DashboardState,
    onEvent: (HomeEvent) -> Unit = {},
) {
    listOf(
        state.moodRecords,
        state.user,
        state.stressLevelRecords,
        state.selfJournalRecords,
        state.sleepQualityRecords
    ).foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { moodRecords: List<MoodRecordResource>, u: User, stressLevels: List<StressLevelRecordResource>, selfJournals: List<SelfJournalRecordResource>, sleepQualities: List<SleepQualityRecordResource> ->
            throw NotImplementedError()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                HomeTopBar(
                    modifier = Modifier
                        .padding(top = padding.calculateTopPadding()),
                    user = u,
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.freud_score,
                    onSeeAll = { onEvent(HomeEvent.OnNavigateToFreudScore) }
                )
                FreudScoreMetric(
                    freudScore = state.freudScore,
                    onClick = { onEvent(HomeEvent.OnNavigateToFreudScore) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.mood,
                    onSeeAll = { onEvent(HomeEvent.OnNavigateToMood) }
                )
                MoodMetric(
                    records = moodRecords,
                    containerColor = Colors.White,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddMood) },
                    onClick = { }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.sleep,
                    onSeeAll = { onEvent(HomeEvent.OnNavigateToSleepQuality) }
                )
                SleepQualityMetric(
                    records = sleepQualities,
                    containerColor = Colors.White,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddSleep) },
                    onClick = { onEvent(HomeEvent.OnNavigateToSleepQuality) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.stress,
                    onSeeAll = { onEvent(HomeEvent.OnNavigateToStressLevel) }
                )
                StressLevelMetric(
                    records = stressLevels,
                    containerColor = Colors.White,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddStressLevel) },
                    onClick = { onEvent(HomeEvent.OnNavigateToStressLevel) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.self_journaling,
                    onSeeAll = { onEvent(HomeEvent.OnNavigateToSelfJournal) }
                )
                SelfJournalingMetric(
                    records = selfJournals,
                    containerColor = Colors.White,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddJournaling) },
                    onClick = { onEvent(HomeEvent.OnNavigateToSelfJournal) }
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
        state = DashboardState(
            freudScore = FreudScoreResource.Healthy(80),
            moodRecords = UiState.Success(
                listOf(
                    MoodRecordResource()
                )
            ),
            stressLevelRecords = UiState.Success(
                listOf(
                    StressLevelRecordResource()
                )
            ),
            selfJournalRecords = UiState.Success(
                listOf(
                    SelfJournalRecordResource()
                )
            ),
            sleepQualityRecords = UiState.Success(
                listOf(
                    SleepQualityRecordResource()
                )
            ),
            user = UiState.Success(
                User()
            )
        ),
    )
}
