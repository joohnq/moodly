package com.joohnq.home.impl.presentation.home

import androidx.compose.runtime.Composable
import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.User
import com.joohnq.home.impl.ui.presentation.dashboard.DashboardContract
import com.joohnq.home.impl.ui.presentation.home.HomeContent
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.sleep_quality.impl.ui.parameter.ListSleepQualityRecordResourceParameterProvider
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.impl.ui.parameter.ListStressLevelRecordResourceParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    moodRecordResources: List<MoodRecordResource>,
    @PreviewParameter(ListStressLevelRecordResourceParameterProvider::class)
    stressLevelRecordResources: List<StressLevelRecordResource>,
    @PreviewParameter(ListSleepQualityRecordResourceParameterProvider::class)
    sleepQualityRecordResources: List<SleepQualityRecordResource>,
) {
    HomeContent(
        state =
            DashboardContract.State(
                moodItems = moodRecordResources,
                stressLevelItems = stressLevelRecordResources,
                selfJournalItems = SelfJournalRecordResource.allSelfJournalRecordResourcePreview,
                sleepQualityItems = sleepQualityRecordResources,
                user =
                    User(
                        name = "John Doe",
                        image = "0",
                        imageType = ImageType.DRAWABLE
                    )
            )
    )
}
