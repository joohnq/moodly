package com.joohnq.mood.impl.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.presentation.mood.MoodContract
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.description
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun MoodContentBody(
    modifier: Modifier = Modifier,
    record: MoodRecordResource? = null,
    records: List<MoodRecordResource>,
    onEvent: (MoodContract.Event) -> Unit = {},
    onAction: (MoodContract.Intent) -> Unit = {},
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
        onCreate = { onEvent(MoodContract.Event.OnAddMood) }
    )
    MoodCalendar(
        modifier = modifier,
        records = records,
        onCreate = { onEvent(MoodContract.Event.OnAddMood) }
    )
    MoodHistoryContent(
        modifier = modifier,
        records = records.take(7),
        onSeeMore = {
            onEvent(MoodContract.Event.OnNavigateToMoodHistory)
        },
        onCreate = {
            onEvent(MoodContract.Event.OnAddMood)
        },
        onDelete = { id ->
            onAction(MoodContract.Intent.Delete(id))
        }
    )
}
