package com.joohnq.mood.add.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundVerticalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.log_first_mood
import com.joohnq.shared_resources.mood_history
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.you_dont_have_enough_data_to_show_your_history_lets_log_your_first_mood_to_see_this

@Composable
fun MoodHistoryContent(
    modifier: Modifier = Modifier,
    items: List<MoodRecordResource>,
    onSeeMore: () -> Unit = {},
    onCreate: () -> Unit = {},
    onDelete: (Int) -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.mood_history,
        onSeeMore = onSeeMore
    )
    if (items.isEmpty()) {
        NotFoundVerticalLayout(
            modifier = modifier,
            title = Res.string.you_dont_have_enough_data_to_show_your_history_lets_log_your_first_mood_to_see_this,
            actionText = Res.string.log_first_mood,
            image = Drawables.Images.MoodHistory,
            onClick = onCreate
        )
    } else {
        MoodHistoryBody(
            modifier = modifier,
            items = items,
            onDelete = onDelete
        )
    }
}
