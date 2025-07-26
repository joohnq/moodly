package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.home.impl.ui.components.WeekMoodIndicator
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.parameter.MoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.resource.MoodResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun WeekMoodIndicatorPreview(
    @PreviewParameter(MoodRecordResourceParameterProvider::class)
    item: MoodRecordResource,
) {
    WeekMoodIndicator(
        records = listOf(item),
        resource = item.mood
    )
}

@Preview
@Composable
fun WeekMoodIndicatorListPreview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
    WeekMoodIndicator(
        records = list,
        resource = MoodResource.Neutral
    )
}
