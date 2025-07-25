package com.joohnq.freud_score.impl.presentation.freud_score

import androidx.compose.runtime.Composable
import com.joohnq.freud_score.impl.parameter.FreudScoreResourceParameterProvider
import com.joohnq.freud_score.impl.resource.FreudScoreResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun FreudScoreContentPreview(
    @PreviewParameter(FreudScoreResourceParameterProvider::class) item: FreudScoreResource,
) {
    FreudScoreContent(
        state = FreudScoreContract.State(
            freudScore = item
        ),
    )
}