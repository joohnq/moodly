package com.joohnq.mood.impl.ui.presentation.add_mood

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.fake.depressedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.happyMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.neutralMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.overjoyedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.sadMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.presentation.add_mood.viewmodel.AddMoodState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AddMoodContentDepressedPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = depressedMoodRecordResourcePreview
        ),
    )
}

@Preview
@Composable
fun AddMoodContentSadPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = sadMoodRecordResourcePreview
        ),
    )
}

@Preview
@Composable
fun AddMoodContentNeutralPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = neutralMoodRecordResourcePreview
        ),
    )
}

@Preview
@Composable
fun AddMoodContentHappyPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = happyMoodRecordResourcePreview
        ),
    )
}

@Preview
@Composable
fun AddMoodContentOverjoyedPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = overjoyedMoodRecordResourcePreview
        ),
    )
}