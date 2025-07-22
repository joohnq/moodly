package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodMetricPreview() {
    MoodMetric(
        records = listOf(
            MoodRecordResource(),
            MoodRecordResource(),
            MoodRecordResource(),
        ),
    )
}