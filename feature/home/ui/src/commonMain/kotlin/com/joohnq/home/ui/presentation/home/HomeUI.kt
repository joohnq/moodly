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
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.domain.entity.User
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.home.ui.components.HomeTopBar
import com.joohnq.home.ui.components.MentalHealthMetrics
import com.joohnq.home.ui.components.SelfJournalingMetric
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.Title
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.mental_health_metrics
import com.joohnq.shared_resources.self_journaling
import com.joohnq.shared_resources.sleep
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.components.SleepQualityMetric
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.mapper.toResource
import com.joohnq.stress_level.ui.resource.StressLevelResource

fun List<SleepQualityRecord>.getTodaySleepQualityResource(): SleepQualityResource? =
    find { it.createdAt == getNow() }?.sleepQuality?.toResource()

fun List<StressLevelRecord>.getTodayStressLevelResource(): StressLevelResource? =
    find { it.createdAt == getNow() }?.stressLevel?.toResource()

fun List<HealthJournalRecord>.getTodayHealthJournalRecord(): HealthJournalRecord? =
    find { it.createdAt == getNow() }

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
            val sleepQualityResource = sleepQualities.getTodaySleepQualityResource()
            val stressLevelResource = stressLevels.getTodayStressLevelResource()
            val healthJournalResource = healthJournals.getTodayHealthJournalRecord()

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
                    Title(
                        modifier = Modifier.paddingHorizontalMedium().padding(vertical = 32.dp),
                        text = Res.string.mental_health_metrics
                    )
                    MentalHealthMetrics(
                        freudScore = freudScore,
                        statsRecord = statsRecords.first(),
                        healthJournal = healthJournals,
                        onEvent = onEvent
                    )
                    SectionHeader(
                        modifier = Modifier.paddingHorizontalMedium(),
                        title = Res.string.sleep,
                        onSeeAll = { onEvent(HomeEvent.OnNavigateToSleepHistory) }
                    )
                    SleepQualityMetric(
                        resource = sleepQualityResource,
                        onCreate = { onEvent(HomeEvent.OnNavigateToAddSleep) },
                        onClick = { onEvent(HomeEvent.OnNavigateToSleepHistory) }
                    )
                    SectionHeader(
                        modifier = Modifier.paddingHorizontalMedium(),
                        title = Res.string.self_journaling,
                        onSeeAll = { onEvent(HomeEvent.OnNavigateToAllJournals) }
                    )
                    SelfJournalingMetric(
                        resource = healthJournalResource,
                        onCreate = { onEvent(HomeEvent.OnNavigateToAddJournaling) },
                        onClick = { onEvent(HomeEvent.OnNavigateToStressHistory) }
                    )
                    VerticalSpacer(16.dp)
                    VerticalSpacer(30.dp)
                }
            }
        }
    )
}