package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.fake.depressedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.happyMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.neutralMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.overjoyedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.sadMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DescriptionSectionDepressedPreview() {
    DescriptionSection(
        record = depressedMoodRecordResourcePreview
    )
}

@Preview
@Composable
fun DescriptionSectionSadPreview() {
    DescriptionSection(
        record = sadMoodRecordResourcePreview
    )
}

@Preview
@Composable
fun DescriptionSectionNeutralPreview() {
    DescriptionSection(
        record = neutralMoodRecordResourcePreview
    )
}

@Preview
@Composable
fun DescriptionSectionHappyPreview() {
    DescriptionSection(
        record = happyMoodRecordResourcePreview
    )
}

@Preview
@Composable
fun DescriptionSectionOverjoyedPreview() {
    DescriptionSection(
        record = overjoyedMoodRecordResourcePreview
    )
}