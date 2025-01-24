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
import com.joohnq.core.ui.mapper.fold
import com.joohnq.core.ui.mapper.getValueOrNull
import com.joohnq.domain.entity.User
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.home.ui.components.HomeTopBar
import com.joohnq.home.ui.components.MentalHealthMetrics
import com.joohnq.home.ui.components.MindfulTracker
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.Title
import com.joohnq.shared_resources.mental_health_metrics
import com.joohnq.shared_resources.mindful_tracker
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.mapper.toResource

@Composable
fun HomeUI(
    today: String = "",
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
        sleepQuality,
    ).fold(
        onLoading = { LoadingUI() },
    ) {
        val user = user.getValueOrNull()

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
                    userName = user.name,
                    images = Drawables.Avatar.avatars,
                    image = user.image,
                    imageType = user.imageType,
                    date = today
                )
                Title(Res.string.mental_health_metrics)
                MentalHealthMetrics(
                    freudScore = freudScore,
                    statsRecord = statsRecord.getValueOrNull().first(),
                    healthJournal = healthJournal.getValueOrNull(),
                    onEvent = onEvent
                )
                Title(Res.string.mindful_tracker)
                MindfulTracker(
                    sleepQuality = sleepQuality.getValueOrNull()
                        .first().sleepQuality.toResource(),
                    stressLevel = stressLevel.getValueOrNull()
                        .first().stressLevel.toResource(),
                    moodTracker = statsRecord.getValueOrNull().take(3)
                        .reversed()
                        .map { statsRecord -> statsRecord.mood.toResource() },
                    onAction = onEvent
                )
            }
        }
    }
}