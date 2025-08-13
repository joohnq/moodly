package com.joohnq.mood.add.presentation

import androidx.compose.runtime.Composable
import com.joohnq.add.presentation.AddMoodContent
import com.joohnq.add.presentation.AddMoodContract
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.MoodRecordResourceParameterProvider
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(MoodRecordResourceParameterProvider::class)
    item: MoodRecordResource,
) {
    AddMoodContent(
        state =
            AddMoodContract.State(
                item = item
            )
    )
}
