package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.parameter.MoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun MoodHistoryCardPreview(
    @PreviewParameter(MoodRecordResourceParameterProvider::class)
    item: MoodRecordResource,
) {
    MoodHistoryCard(
        record = item
    )
}
