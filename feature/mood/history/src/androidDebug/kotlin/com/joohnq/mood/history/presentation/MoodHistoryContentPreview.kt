package com.joohnq.mood.history.presentation

import androidx.compose.runtime.Composable
import com.joohnq.history.presentation.MoodHistoryContent
import com.joohnq.history.presentation.MoodHistoryContract
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
    MoodHistoryContent(
        state =
            MoodHistoryContract.State(
                items = list
            )
    )
}

@Preview
@Composable
fun LoadingPreview() {
    MoodHistoryContent(
        state =
            MoodHistoryContract.State(
                isLoading = true
            )
    )
}

@Preview
@Composable
fun ErrorPreview() {
    MoodHistoryContent(
        state =
            MoodHistoryContract.State(
                isError = "Some error"
            )
    )
}
