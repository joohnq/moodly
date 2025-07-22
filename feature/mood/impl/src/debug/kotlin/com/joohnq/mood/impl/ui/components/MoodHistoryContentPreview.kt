package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.fake.moodRecordsResourcesListPreview
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodHistoryContentPreview() {
    MoodHistoryContent(
        records = moodRecordsResourcesListPreview,
    )
}