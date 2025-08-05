package com.joohnq.mood.history.presentation

import androidx.compose.runtime.Composable
import com.joohnq.history.presentation.MoodHistoryContent
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
    MoodHistoryContent(
        records = UiState.Success(list)
    )
}

@Preview
@Composable
fun LoadingPreview() {
    MoodHistoryContent(
        records = UiState.Loading
    )
}

@Preview
@Composable
fun ErrorPreview() {
    MoodHistoryContent(
        records = UiState.Error("Some error")
    )
}
