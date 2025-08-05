package com.joohnq.mood.overview.presentation

import androidx.compose.runtime.Composable
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.overview.presentation.MoodOverviewContent
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
    MoodOverviewContent(
        records = UiState.Success(list)
    )
}

@Preview
@Composable
private fun LoadingPreview() {
    MoodOverviewContent(
        records = UiState.Loading
    )
}

@Preview
@Composable
private fun ErrorPreview() {
    MoodOverviewContent(
        records = UiState.Error("Some error")
    )
}
