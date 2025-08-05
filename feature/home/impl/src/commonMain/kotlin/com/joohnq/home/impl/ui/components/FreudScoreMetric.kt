package com.joohnq.home.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.freud_score.impl.ui.resource.FreudScoreResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.card.MetricSummaryCard
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import org.jetbrains.compose.resources.stringResource

@Composable
fun FreudScoreMetric(
    freudScore: FreudScoreResource?,
    onClick: () -> Unit = {},
) {
    if (freudScore == null) return
    SectionHeader(
        modifier = Modifier.paddingHorizontalMedium(),
        title = Res.string.freud_score
    )
    MetricSummaryCard(
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