package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource.Companion.selfJournalRecordResourceDepressedPreview
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource.Companion.selfJournalRecordResourceHappyPreview
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource.Companion.selfJournalRecordResourceNeutralPreview
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource.Companion.selfJournalRecordResourceOverjoyedPreview
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource.Companion.selfJournalRecordResourceSadPreview
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SelfJournalHistoryCardDepressedPreview() {
    SelfJournalHistoryCard(
        record = selfJournalRecordResourceDepressedPreview,
    )
}

@Preview
@Composable
fun SelfJournalHistoryCardSadPreview() {
    SelfJournalHistoryCard(
        record = selfJournalRecordResourceSadPreview,
    )
}

@Preview
@Composable
fun SelfJournalHistoryCardNeutralPreview() {
    SelfJournalHistoryCard(
        record = selfJournalRecordResourceNeutralPreview,
    )
}

@Preview
@Composable
fun SelfJournalHistoryCardHappyPreview() {
    SelfJournalHistoryCard(
        record = selfJournalRecordResourceHappyPreview,
    )
}

@Preview
@Composable
fun SelfJournalHistoryCardOverjoyedPreview() {
    SelfJournalHistoryCard(
        record = selfJournalRecordResourceOverjoyedPreview,
    )
}

@Preview
@Composable
fun SelfJournalStatsCardPreview() {
    SelfJournalStatsCard(
        icon = Drawables.Icons.Outlined.Logo,
        title = "Title",
        color = Colors.Brown80,
        backgroundColor = Colors.White,
        description = "Description"
    )
}
