package com.joohnq.mood.impl.ui.presentation.mood_history

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodHistoryContentPreview() {
    MoodHistoryContent(
        records = UiState.Success(
            MoodRecordResource.allMoodRecordResourcePreview
        ),
    )
}

@Preview
@Composable
fun MoodHistoryContentEmptyPreview() {
    MoodHistoryContent(
        records = UiState.Success(
            listOf()
        )
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