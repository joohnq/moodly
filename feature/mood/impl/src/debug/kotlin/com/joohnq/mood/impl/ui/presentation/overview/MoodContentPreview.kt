package com.joohnq.mood.impl.ui.presentation.overview

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.parameter.MoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun MoodContentPreview(
    @PreviewParameter(MoodRecordResourceParameterProvider::class)
    item: MoodRecordResource,
) {
    MoodOverviewContent(
        records =
            UiState.Success(
                listOf(item)
            )
    )
}
