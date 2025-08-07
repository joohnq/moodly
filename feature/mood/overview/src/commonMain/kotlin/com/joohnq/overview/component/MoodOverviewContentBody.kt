package com.joohnq.overview.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.add.ui.components.MoodHistoryContent
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.overview.presentation.MoodOverviewContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.description
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun MoodOverviewContentBody(
    modifier: Modifier = Modifier,
    item: MoodRecordResource? = null,
    items: List<MoodRecordResource>,
    onEvent: (MoodOverviewContract.Event) -> Unit = {},
    onIntent: (MoodOverviewContract.Intent) -> Unit = {},
) {
    if (item != null) {
        SectionHeader(
            modifier = modifier,
            title = Res.string.description
        )
        Text(
            text = item.description,
            style = TextStyles.textMdSemiBold(),
            color = Colors.Brown80,
            modifier = modifier
        )
        VerticalSpacer(20.dp)
    }
    MoodOverviewInsight(
        modifier = modifier,
        items = items,
        onCreate = { onEvent(MoodOverviewContract.Event.NavigateToAddMood) }
    )
    MoodOverviewCalendar(
        modifier = modifier,
        items = items,
        onCreate = { onEvent(MoodOverviewContract.Event.NavigateToAddMood) }
    )
    MoodHistoryContent(
        modifier = modifier,
        items = items.take(7),
        onSeeMore = {
            onEvent(MoodOverviewContract.Event.NavigateToMoodHistory)
        },
        onCreate = {
            onEvent(MoodOverviewContract.Event.NavigateToAddMood)
        },
        onDelete = { id ->
            onIntent(MoodOverviewContract.Intent.Delete(id))
        }
    )
}
