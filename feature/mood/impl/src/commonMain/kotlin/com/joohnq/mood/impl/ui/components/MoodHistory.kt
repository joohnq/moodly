package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.NotFoundVerticalLayout
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.log_first_mood
import com.joohnq.shared_resources.mood_history
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.you_dont_have_enough_data_to_show_your_history_lets_log_your_first_mood_to_see_this

@Composable
fun MoodHistory(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.White,
    records: List<MoodRecordResource>,
    onSeeMore: () -> Unit = {},
    onCreate: () -> Unit = {},
    onDelete: (Int) -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.mood_history,
        onSeeMore = onSeeMore
    )
    if (records.isEmpty())
        NotFoundVerticalLayout(
            modifier = modifier,
            containerColor = containerColor,
            title = Res.string.you_dont_have_enough_data_to_show_your_history_lets_log_your_first_mood_to_see_this,
            subtitle = Res.string.log_first_mood,
            image = Drawables.Images.MoodHistory,
            onClick = onCreate,
        )
    else
        MoodHistoryContent(
            modifier = modifier,
            containerColor = containerColor,
            records = records,
            onDelete = onDelete
        )
}