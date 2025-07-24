package com.joohnq.mood.impl.ui.presentation.add_mood

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.parameter.MoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.presentation.add_mood.viewmodel.AddMoodState
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun AddMoodContentPreview(
    @PreviewParameter(MoodRecordResourceParameterProvider::class)
    item: MoodRecordResource
) {
    AddMoodContent(
        state = AddMoodState(
            record = item
        ),
    )
}