package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.home.impl.ui.components.WeekMoodIndicator
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.parameter.MoodRecordResourceParameterProvider
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(MoodRecordResourceParameterProvider::class)
    item: MoodRecordResource,
) {
    WeekMoodIndicator(
        items = listOf(item),
        resource = item.mood
    )
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
    WeekMoodIndicator(
        items = list,
        resource = MoodResource.Neutral
    )
}