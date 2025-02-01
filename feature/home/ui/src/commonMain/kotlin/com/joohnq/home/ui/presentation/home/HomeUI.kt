package com.joohnq.home.ui.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.domain.entity.User
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.home.ui.components.FreudScoreMetric
import com.joohnq.home.ui.components.HomeTopBar
import com.joohnq.home.ui.components.MoodMetric
import com.joohnq.home.ui.components.SelfJournalingMetric
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.components.SleepQualityMetric
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.components.StressLevelMetric
import com.joohnq.stress_level.ui.mapper.getTodayStressLevelResource

@Composable
fun HomeUI(
    user: UiState<User>,
    statsRecord: UiState<List<StatsRecord>>,
    sleepQuality: UiState<List<SleepQualityRecord>>,
    stressLevel: UiState<List<StressLevelRecord>>,
    freudScore: FreudScoreResource? = null,
    healthJournal: UiState<List<HealthJournalRecord>>,
    onEvent: (HomeEvent) -> Unit = {},
) {
    listOf(
        statsRecord,
        user,
        stressLevel,
        healthJournal,
        sleepQuality
    ).foldComposable(
        onLoading = { LoadingUI() },
        onError = { onEvent(HomeEvent.ShowError(it)) },
        onSuccess = { statsRecords: List<StatsRecord>, u: User, stressLevels: List<StressLevelRecord>, healthJournals: List<HealthJournalRecord>, sleepQualities: List<SleepQualityRecord> ->
            val stressLevelResource = stressLevels.getTodayStressLevelResource()

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
                        modifier = Modifier
                            .padding(top = padding.calculateTopPadding()),
                        user = u,
                    )
                    SectionHeader(
                        modifier = Modifier.paddingHorizontalMedium(),
                        title = Res.string.freud_score,
                        onSeeAll = { onEvent(HomeEvent.OnNavigateToStressLevel) }
                    )
                    FreudScoreMetric(
                        freudScore = freudScore,
                        onClick = { onEvent(HomeEvent.OnNavigateToStressLevel) }
                    )
                    SectionHeader(
                        modifier = Modifier.paddingHorizontalMedium(),
                        title = Res.string.mood,
                        onSeeAll = { onEvent(HomeEvent.OnNavigateToMood) }
                    )
                    MoodMetric(
                        records = statsRecords,
                        onCreate = { },
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
                        resource = stressLevelResource,
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
                    VerticalSpacer(16.dp)
                    VerticalSpacer(30.dp)
                }
            }
        }
    )
}
