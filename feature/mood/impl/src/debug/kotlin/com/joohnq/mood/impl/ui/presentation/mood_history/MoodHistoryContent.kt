package com.joohnq.mood.impl.ui.presentation.mood_history

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.fake.depressedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.happyMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.neutralMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.overjoyedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.sadMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.ui.entity.UiState
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodHistoryContentEmptyPreview() {
    MoodHistoryContent(
        records = UiState.Success(
            listOf(
                MoodRecordResource(
                    createdAt = LocalDateTime(2025, 1, 1, 0, 0, 0)
                )
            )
        ),
    )
}

@Preview
@Composable
fun MoodHistoryContentLoadingPreview() {
    MoodHistoryContent(
        records = UiState.Loading
    )
}

@Preview
@Composable
fun MoodHistoryContentErrorPreview() {
    MoodHistoryContent(
        records = UiState.Error("Something went wrong")
    )
}

@Preview
@Composable
fun MoodHistoryContentDepressedPreview() {
    MoodHistoryContent(
        records = UiState.Success(
            listOf(
                depressedMoodRecordResourcePreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodHistoryContentSadPreview() {
    MoodHistoryContent(
        records = UiState.Success(
            listOf(
                sadMoodRecordResourcePreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodHistoryContentNeutralPreview() {
    MoodHistoryContent(
        records = UiState.Success(
            listOf(
                neutralMoodRecordResourcePreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodHistoryContentHappyPreview() {
    MoodHistoryContent(
        records = UiState.Success(
            listOf(
                happyMoodRecordResourcePreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodHistoryContentOverjoyedPreview() {
    MoodHistoryContent(
        records = UiState.Success(
            listOf(
                overjoyedMoodRecordResourcePreview
            )
        ),
    )
}