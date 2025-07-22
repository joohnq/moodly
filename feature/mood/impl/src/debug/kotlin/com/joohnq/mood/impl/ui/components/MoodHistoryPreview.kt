package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.mood.impl.ui.fake.moodRecordsResourcesListPreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.NotFoundVertical
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.log_first_mood
import com.joohnq.shared_resources.mood_history
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.you_dont_have_enough_data_to_show_your_history_lets_log_your_first_mood_to_see_this
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