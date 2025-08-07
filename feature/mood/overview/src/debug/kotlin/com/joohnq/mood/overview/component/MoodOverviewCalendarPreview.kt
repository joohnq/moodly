package com.joohnq.mood.overview.component

import androidx.compose.runtime.Composable
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.overview.component.MoodOverviewCalendar
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Composable
@Preview
private fun Preview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
    MoodOverviewCalendar(
        items = list
    )
}
