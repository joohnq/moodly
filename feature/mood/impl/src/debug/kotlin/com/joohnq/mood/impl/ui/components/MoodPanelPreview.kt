package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodPanelDepressedPreview() {
    MoodContentPanel(
        record = MoodRecordResource.moodRecordResourceDepressedPreview
    )
}

@Preview
@Composable
fun MoodPanelSadPreview() {
    MoodContentPanel(
        record = MoodRecordResource.moodRecordResourceSadPreview
    )
}

@Preview
@Composable
fun MoodPanelNeutralPreview() {
    MoodContentPanel(
        record = MoodRecordResource.moodRecordResourceNeutralPreview
    )
}

@Preview
@Composable
fun MoodPanelHappyPreview() {
    MoodContentPanel(
        record = MoodRecordResource.moodRecordResourceHappyPreview
    )
}

@Preview
@Composable
fun MoodPanelOverjoyedPreview() {
    MoodContentPanel(
        record = MoodRecordResource.moodRecordResourceOverjoyedPreview
    )
}