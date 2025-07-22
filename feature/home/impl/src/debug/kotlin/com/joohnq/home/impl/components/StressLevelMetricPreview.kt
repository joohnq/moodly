package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.freud_score.impl.resource.FreudScoreResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.MetricCardSide
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FreudScoreMetricUnhealthyPreview() {
    FreudScoreMetric(
        freudScore = FreudScoreResource.Unhealthy(0)
    )
}

@Preview
@Composable
fun FreudScoreMetricAtRiskPreview() {
    FreudScoreMetric(
        freudScore = FreudScoreResource.AtRisk(25),
    )
}

@Preview
@Composable
fun FreudScoreMetricStablePreview() {
    FreudScoreMetric(
        freudScore = FreudScoreResource.Stable(50),
    )
}

@Preview
@Composable
fun FreudScoreMetricHealthyPreview() {
    FreudScoreMetric(
        freudScore = FreudScoreResource.Healthy(75),
    )
}

@Preview
@Composable
fun FreudScoreMetricMostlyHealthyPreview() {
    FreudScoreMetric(
        freudScore = FreudScoreResource.MostlyHealthy(100),
    )
}