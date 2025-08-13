package com.joohnq.mood.overview.presentation

import androidx.compose.runtime.Composable
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.overview.presentation.MoodOverviewContent
import com.joohnq.overview.presentation.MoodOverviewContract
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
    MoodOverviewContent(
        state =
            MoodOverviewContract.State(
                items = list
            )
    )
}

@Preview
@Composable
private fun LoadingPreview() {
    MoodOverviewContent(
        state =
            MoodOverviewContract.State(
                isLoading = true
            )
    )
}

@Preview
@Composable
private fun ErrorPreview() {
    MoodOverviewContent(
        state =
            MoodOverviewContract.State(
                isError = "Some error"
            )
    )
}
