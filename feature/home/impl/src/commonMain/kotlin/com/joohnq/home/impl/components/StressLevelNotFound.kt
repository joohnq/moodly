package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.api.mapper.toMonthNameString
import com.joohnq.self_journal.impl.ui.components.JournalCalendar
import com.joohnq.self_journal.impl.ui.mapper.getTodaySelfJournalRecord
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.journals_written_in
import com.joohnq.shared_resources.lets_set_up_daily_journaling_and_self_reflection
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalingMetric(
    records: List<SelfJournalRecordResource>,
    onCreate: () -> Unit = {},
) {
    val resource = records.getTodaySelfJournalRecord()

    if (resource == null)
        NotFoundHorizontal(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
            title = Res.string.lets_set_up_daily_journaling_and_self_reflection,
            subtitle = Res.string.add_new_journal,
            image = Drawables.Images.SelfJournalCreate,
            onClick = onCreate
        )
    else {
        JournalCalendar(
            modifier = Modifier.paddingHorizontalMedium(),
            records = records,
            subtitle = stringResource(
                Res.string.journals_written_in,
                resource.createdAt.toMonthNameString()
            ),
            onCreate = onCreate,
        )
    }
}