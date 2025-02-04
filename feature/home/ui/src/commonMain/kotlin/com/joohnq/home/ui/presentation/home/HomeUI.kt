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
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.domain.entity.User
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.home.ui.components.FreudScoreMetric
import com.joohnq.home.ui.components.HomeTopBar
import com.joohnq.home.ui.components.MoodMetric
import com.joohnq.home.ui.components.SelfJournalingMetric
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.home.ui.presentation.viewmodel.DashboardState
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.components.SleepQualityMetric
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.components.StressLevelMetric

@Composable
fun HomeUI(
    padding: PaddingValues,
    state: DashboardState,
    onEvent: (HomeEvent) -> Unit = {},
) {
    listOf(
        state.statsRecords,
        state.user,
        state.stressLevelRecords,
        state.healthJournalRecords,
        state.sleepQualityRecords
    ).foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { statsRecords: List<StatsRecord>, u: User, stressLevels: List<StressLevelRecord>, healthJournals: List<HealthJournalRecord>, sleepQualities: List<SleepQualityRecord> ->
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
                    records = statsRecords,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddStat) },
                    onClick = { }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.sleep,
                    onSeeAll = { onEvent(HomeEvent.OnNavigateToSleepQuality) }
                )
                SleepQualityMetric(
                    records = sleepQualities,
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
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddStress) },
                    onClick = { onEvent(HomeEvent.OnNavigateToStressLevel) }
                )
                SectionHeader(
                    modifier = Modifier.paddingHorizontalMedium(),
                    title = Res.string.self_journaling,
                    onSeeAll = { onEvent(HomeEvent.OnNavigateToAllJournals) }
                )
                SelfJournalingMetric(
                    records = healthJournals,
                    onCreate = { onEvent(HomeEvent.OnNavigateToAddJournaling) },
                    onClick = { onEvent(HomeEvent.OnNavigateToHealthJournal) }
                )
                VerticalSpacer(padding.calculateBottomPadding() + 10.dp)
            }
        }
    )
}
