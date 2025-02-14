package com.joohnq.home.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.MetricCardSide
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FreudScoreMetric(freudScore: FreudScoreResource?, onClick: () -> Unit = {}) {
    if (freudScore == null) return
    MetricCardSide(
        modifier = Modifier.paddingHorizontalMedium(),
        icon = Drawables.Icons.Filled.Logo,
        dark = true,
        containerColor = freudScore.palette.backgroundColor,
        title = stringResource(Res.string.freud_score),
        text = freudScore.score.toString(),
        suffix = stringResource(Res.string.freud_score),
        description = stringResource(freudScore.title),
        content = {

        },
        color = Colors.White,
        onClick = onClick
    )
}

@Preview
@Composable
fun FreudScoreMetricPreview() {
    FreudScoreMetric(
        freudScore = FreudScoreResource.Healthy(50),
        onClick = {}
    )
}