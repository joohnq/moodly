package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun JournalHistoryCardDepressedPreview() {
    JournalHistoryCard(
        record = SelfJournalRecordResource.selfJournalRecordResourceDepressedPreview
    )
}

@Preview
@Composable
fun JournalHistoryCardSadPreview() {
    JournalHistoryCard(
        record = SelfJournalRecordResource.selfJournalRecordResourceSadPreview
    )
}

@Preview
@Composable
fun JournalHistoryCardNeutralPreview() {
    JournalHistoryCard(
        record = SelfJournalRecordResource.selfJournalRecordResourceNeutralPreview
    )
}

@Preview
@Composable
fun JournalHistoryCardHappyPreview() {
    JournalHistoryCard(
        record = SelfJournalRecordResource.selfJournalRecordResourceHappyPreview
    )
}

@Preview
@Composable
fun JournalHistoryCardOverjoyedPreview() {
    JournalHistoryCard(
        record = SelfJournalRecordResource.selfJournalRecordResourceOverjoyedPreview
    )
}