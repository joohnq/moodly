package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.fake.moodRecordsResourcesListPreview
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodHistoryPreview() {
    MoodHistory(
        records = moodRecordsResourcesListPreview,
    )
}

@Preview
@Composable
fun MoodHistoryPreviewEmpty() {
    MoodHistory(
        records = listOf(),
    )
}