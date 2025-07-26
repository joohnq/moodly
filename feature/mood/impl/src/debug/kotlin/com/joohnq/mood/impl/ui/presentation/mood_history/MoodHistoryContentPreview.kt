package com.joohnq.mood.impl.ui.presentation.mood_history

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun MoodHistoryContentPreview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>
) {
    MoodHistoryContent(
        records =
        UiState.Success(
            list
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
