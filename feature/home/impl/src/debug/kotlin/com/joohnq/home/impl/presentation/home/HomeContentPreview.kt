package com.joohnq.home.impl.presentation.home

import androidx.compose.runtime.Composable
import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.User
import com.joohnq.freud_score.impl.ui.resource.FreudScoreResource
import com.joohnq.home.impl.ui.presentation.viewmodel.DashboardContract
import com.joohnq.home.impl.ui.presentation.home.HomeContent
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.sleep_quality.impl.ui.parameter.ListSleepQualityRecordResourceParameterProvider
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.impl.ui.parameter.ListStressLevelRecordResourceParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun HomeContentPreview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    moodRecordResources: List<MoodRecordResource>,
    @PreviewParameter(ListStressLevelRecordResourceParameterProvider::class)
    stressLevelRecordResources: List<StressLevelRecordResource>,
    @PreviewParameter(ListSleepQualityRecordResourceParameterProvider::class)
    sleepQualityRecordResources: List<SleepQualityRecordResource>
) {
    HomeContent(
        state = DashboardContract.State(
            freudScore = FreudScoreResource.Healthy(80),
            moodRecords = UiState.Success(
                moodRecordResources
            ),
            stressLevelRecords = UiState.Success(
                stressLevelRecordResources
            ),
            selfJournalRecords = UiState.Success(
                SelfJournalRecordResource.allSelfJournalRecordResourcePreview
            ),
            sleepQualityRecords = UiState.Success(
                sleepQualityRecordResources
            ),
            user = UiState.Success(
                User(
                    name = "John Doe",
                    image = "0",
                    imageType = ImageType.DRAWABLE
                )
            )
        )
    )
}