package com.joohnq.freud_score.impl.presentation.freud_score

import androidx.compose.runtime.Composable
import com.joohnq.freud_score.impl.parameter.FreudScoreResourceParameterProvider
import com.joohnq.freud_score.impl.ui.resource.FreudScoreResource
import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreContent
import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreContract
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun FreudScoreContentPreview(
    @PreviewParameter(FreudScoreResourceParameterProvider::class) item: FreudScoreResource
) {
    FreudScoreContent(
        state = FreudScoreContract.State(
            freudScore = item
        )
    )
}