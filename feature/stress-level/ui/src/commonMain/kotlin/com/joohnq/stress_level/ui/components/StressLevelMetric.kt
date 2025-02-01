package com.joohnq.stress_level.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.FivePackIndicator
import com.joohnq.shared_resources.components.MetricCardSide
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.stress_level.ui.mapper.getBrushGradient
import com.joohnq.stress_level.ui.resource.StressLevelResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressLevelMetric(resource: StressLevelResource?, onCreate: () -> Unit = {}, onClick: () -> Unit = {}) {
    if (resource == null)
        NotFoundHorizontal(
            modifier = Modifier.paddingHorizontalMedium(),
            title = Res.string.lets_set_up_daily_stress_level,
            subtitle = Res.string.add_new_journal,
            image = Drawables.Images.StressLevelManIllustration,
            onClick = onCreate
        )
    else
        MetricCardSide(
            modifier = Modifier.paddingHorizontalMedium(),
            icon = Drawables.Icons.Warning,
            title = stringResource( Res.string.stress_level),
            text = stringResource(resource.value),
            suffix = stringResource(Res.string.level),
            description = stringResource(resource.subtitle),
            content = {
                FivePackIndicator(resource.level, ::getBrushGradient)
            },
            color = Colors.Orange40,
            onClick = onClick
        )
}

@Preview
@Composable
fun StressLevelMetricPreviewOne() {
    StressLevelMetric(
        resource = StressLevelResource.One
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewTwo() {
    StressLevelMetric(
        resource = StressLevelResource.Two
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewThree() {
    StressLevelMetric(
        resource = StressLevelResource.Three
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewFour() {
    StressLevelMetric(
        resource = StressLevelResource.Four
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewFive() {
    StressLevelMetric(
        resource = StressLevelResource.Five
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewNull() {
    StressLevelMetric(
        resource = null
    )
}
