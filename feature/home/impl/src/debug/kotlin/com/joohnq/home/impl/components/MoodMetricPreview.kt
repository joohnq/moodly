package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.home.impl.ui.components.MoodMetric
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun MoodMetricPreview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
    MoodMetric(
        records = list
    )
}
