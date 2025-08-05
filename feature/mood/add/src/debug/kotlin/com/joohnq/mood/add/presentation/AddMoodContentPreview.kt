package com.joohnq.mood.add.presentation

import androidx.compose.runtime.Composable
import com.joohnq.add.presentation.AddMoodContent
import com.joohnq.add.presentation.AddMoodContract
import com.joohnq.mood.add.ui.parameter.MoodRecordResourceParameterProvider
import com.joohnq.mood.add.ui.resource.MoodRecordResource
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
                record = item
            )
    )
}
