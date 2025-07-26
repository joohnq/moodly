package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.freud_score.impl.parameter.FreudScoreResourceParameterProvider
import com.joohnq.freud_score.impl.resource.FreudScoreResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun FreudScoreMetricPreview(
    @PreviewParameter(FreudScoreResourceParameterProvider::class)
    item: FreudScoreResource
) {
    FreudScoreMetric(
        freudScore = item
    )
}
