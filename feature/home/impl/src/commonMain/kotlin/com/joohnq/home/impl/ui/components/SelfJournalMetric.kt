package com.joohnq.home.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.api.mapper.LocalDateTimeMapper.toMonthNameString
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.getTodaySelfJournalRecord
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.presentation.components.SelfJournalOverviewCalendarContent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.card.GiganticCreateCard
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.journals_written_in
import com.joohnq.shared_resources.lets_set_up_daily_journaling_and_self_reflection
import com.joohnq.shared_resources.self_journal
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalMetric(
    items: List<SelfJournalRecordResource>,
    itemsInYear: String,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val resource = items.getTodaySelfJournalRecord()

    SectionHeader(
        modifier = Modifier.paddingHorizontalMedium(),
        title = Res.string.self_journal,
        onSeeMore = onClick
    )
    if (resource == null) {
        NotFoundHorizontalLayout(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
            title = Res.string.lets_set_up_daily_journaling_and_self_reflection,
            actionText = Res.string.add_new_journal,
            image = Drawables.Images.SelfJournalCreate,
            onClick = onCreate
        )
    } else {
        GiganticCreateCard(
            modifier = Modifier.paddingHorizontalMedium(),
            title = itemsInYear,
            subtitle =
                stringResource(
                    Res.string.journals_written_in,
                    resource.createdAt.toMonthNameString()
                ),
            onCreate = onCreate,
            content = {
                SelfJournalOverviewCalendarContent(
                    items = items
                )
            }
        )
    }
}
