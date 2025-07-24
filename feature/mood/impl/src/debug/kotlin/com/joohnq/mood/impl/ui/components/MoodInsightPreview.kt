package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodInsightPreview() {
    MoodInsight(
        records = MoodRecordResource.allMoodRecordResourcePreview,
    )
}