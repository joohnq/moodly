package com.joohnq.mood.overview.component

import androidx.compose.runtime.Composable
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.getWeekStreak
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.overview.component.MoodOverviewInsight
import com.joohnq.shared_resources.components.parameter.BooleanParameterProvider
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
    MoodOverviewInsight(
        isEmpty = false,
        streakDays = list.getWeekStreak()
    )
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(BooleanParameterProvider::class)
    boolean: Boolean,
) {
    MoodOverviewInsight(
        isEmpty = boolean,
        streakDays = 0
    )
}
