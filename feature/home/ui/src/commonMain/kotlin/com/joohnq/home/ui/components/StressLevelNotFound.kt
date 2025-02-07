package com.joohnq.home.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toMonthNameString
import com.joohnq.self_journal.ui.components.JournalCalendar
import com.joohnq.self_journal.ui.mapper.getTodaySelfJournalRecord
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.journals_written_in
import com.joohnq.shared_resources.lets_set_up_daily_journaling_and_self_reflection
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SelfJournalingMetric(
    records: List<SelfJournalRecordResource>,
    containerColor: Color = Colors.White,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val resource = records.getTodaySelfJournalRecord()

    if (resource == null)
        NotFoundHorizontal(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            title = Res.string.lets_set_up_daily_journaling_and_self_reflection,
            subtitle = Res.string.add_new_journal,
            image = Drawables.Images.SelfJournalingIllustration,
            onClick = onCreate
        )
    else {
        JournalCalendar(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            records = records,
            subtitle = stringResource(
                Res.string.journals_written_in,
                resource.createdAt.toMonthNameString()
            ),
            onCreate = onCreate,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
fun SelfJournalingMetricPreviewToday() {
    SelfJournalingMetric(
        records = listOf(
            SelfJournalRecordResource(),
        ),
    )
}

@Preview
@Composable
fun SelfJournalingMetricPreviewYesterday() {
    val now = getNow()

    SelfJournalingMetric(
        records = listOf(
            SelfJournalRecordResource(
                createdAt = LocalDateTime(now.year, now.month, now.date.dayOfMonth.minus(1), 0, 0)
            ),
        ),
    )
}