package com.joohnq.mood.impl.ui.presentation.mood

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodContentDepressedPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                MoodRecordResource.moodRecordResourceDepressedPreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentSadPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                MoodRecordResource.moodRecordResourceSadPreview,
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentNeutralPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                MoodRecordResource.moodRecordResourceNeutralPreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentHappyPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                MoodRecordResource.moodRecordResourceHappyPreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentOverjoyedPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                MoodRecordResource.moodRecordResourceOverjoyedPreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentEmptyPreview() {
    MoodContent(
        records = UiState.Success(
            listOf()
        ),
    )
}

@Preview
@Composable
fun MoodContentLoadingPreview() {
    MoodContent(
        records = UiState.Loading
    )
}

@Preview
@Composable
fun MoodContentErrorPreview() {
    MoodContent(
        records = UiState.Error("Something went wrong")
    )
}