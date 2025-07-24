package com.joohnq.home.impl.presentation.home

import androidx.compose.runtime.Composable
import com.joohnq.api.entity.ImageType
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
        state = DashboardState(
            freudScore = FreudScoreResource.Healthy(80),
            moodRecords = UiState.Success(
                MoodRecordResource.allMoodRecordResourcePreview
            ),
            stressLevelRecords = UiState.Success(
                StressLevelRecordResource.allStressLevelRecordResourcePreview
            ),
            selfJournalRecords = UiState.Success(
                SelfJournalRecordResource.allSelfJournalRecordResourcePreview
            ),
            sleepQualityRecords = UiState.Success(
                SleepQualityRecordResource.allSleepQualityRecordResource
            ),
            user = UiState.Success(
                User(
                    name = "John Doe",
                    image = "0",
                    imageType = ImageType.DRAWABLE
                )
            )
        ),
    )
}