package com.joohnq.home.impl.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.api.entity.User
import com.joohnq.freud_score.impl.resource.FreudScoreResource
import com.joohnq.home.impl.presentation.viewmodel.DashboardState
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HomeContentPreview() {
    HomeContent(
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
