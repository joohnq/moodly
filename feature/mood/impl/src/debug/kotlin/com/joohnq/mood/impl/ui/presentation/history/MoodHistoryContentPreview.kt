package com.joohnq.mood.impl.ui.presentation.history

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.parameter.ListMoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun MoodHistoryContentPreview(
    @PreviewParameter(ListMoodRecordResourceParameterProvider::class)
    list: List<MoodRecordResource>,
) {
}

@Preview
@Composable
fun MoodHistoryContentLoadingPreview() {
}

@Preview
@Composable
fun MoodHistoryContentErrorPreview() {
}
