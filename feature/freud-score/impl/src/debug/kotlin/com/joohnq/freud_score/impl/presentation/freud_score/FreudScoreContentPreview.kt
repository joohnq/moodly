package com.joohnq.freud_score.impl.presentation.freud_score

import androidx.compose.runtime.Composable
import com.joohnq.freud_score.impl.resource.FreudScoreResource
import com.joohnq.freud_score.impl.viewmodel.FreudScoreState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FreudScoreUIPreviewUnhealthy() {
    FreudScoreContent(
        state = FreudScoreState(
            freudScore = FreudScoreResource.Unhealthy(10)
        ),
    )
}

@Preview
@Composable
fun FreudScoreUIPreviewAtRisk() {
    FreudScoreContent(
        state = FreudScoreState(
            freudScore = FreudScoreResource.AtRisk(30)
        ),
    )
}

@Preview
@Composable
fun FreudScoreUIPreviewStable() {
    FreudScoreContent(
        state = FreudScoreState(
            freudScore = FreudScoreResource.Stable(50)
        ),
    )
}

@Preview
@Composable
fun FreudScoreUIPreviewMostlyHealthy() {
    FreudScoreContent(
        state = FreudScoreState(
            freudScore = FreudScoreResource.MostlyHealthy(70)
        ),
    )
}

@Preview
@Composable
fun FreudScoreUIPreviewHealthy() {
    FreudScoreContent(
        state = FreudScoreState(
            freudScore = FreudScoreResource.Healthy(90)
        ),
    )
}