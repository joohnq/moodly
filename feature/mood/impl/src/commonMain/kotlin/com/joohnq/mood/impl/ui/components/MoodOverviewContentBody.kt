package com.joohnq.mood.impl.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.presentation.overview.MoodOverviewContract
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.description
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun MoodOverviewContentBody(
    modifier: Modifier = Modifier,
    record: MoodRecordResource? = null,
    records: List<MoodRecordResource>,
    onEvent: (MoodOverviewContract.Event) -> Unit = {},
    onAction: (MoodOverviewContract.Intent) -> Unit = {},
) {
    if (record != null) {
        SectionHeader(
            modifier = modifier,
            title = Res.string.description
        )
        Text(
            text = record.description,
            style = TextStyles.textMdSemiBold(),
            color = Colors.Brown80,
            modifier = modifier
        )
        VerticalSpacer(20.dp)
    }
    MoodInsight(
        modifier = modifier,
        records = records,
        onCreate = { onEvent(MoodOverviewContract.Event.OnAddMood) }
    )
    MoodCalendar(
        modifier = modifier,
        records = records,
        onCreate = { onEvent(MoodOverviewContract.Event.OnAddMood) }
    )
    MoodHistoryContent(
        modifier = modifier,
        records = records.take(7),
        onSeeMore = {
            onEvent(MoodOverviewContract.Event.OnNavigateToMoodHistory)
        },
        onCreate = {
            onEvent(MoodOverviewContract.Event.OnAddMood)
        },
        onDelete = { id ->
            onAction(MoodOverviewContract.Intent.Delete(id))
        }
    )
}
