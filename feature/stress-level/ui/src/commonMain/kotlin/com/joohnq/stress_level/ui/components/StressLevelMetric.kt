package com.joohnq.stress_level.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.FivePackIndicator
import com.joohnq.shared_resources.components.MetricCardSide
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.lets_set_up_daily_stress_level
import com.joohnq.shared_resources.level
import com.joohnq.shared_resources.stress_level
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.stress_level.ui.mapper.getBrushGradient
import com.joohnq.stress_level.ui.resource.StressLevelResource

@Composable
fun StressLevelNotFound(modifier: Modifier = Modifier, onClick: () -> Unit) {
    NotFoundHorizontal(
        modifier = modifier,
        title = Res.string.lets_set_up_daily_stress_level,
        subtitle = Res.string.add_new_journal,
        image = Drawables.Images.StressLevelManIllustration,
        onClick = onClick
    )
}

@Composable
fun StressLevelMetric(resource: StressLevelResource?, onCreate: () -> Unit, onClick: () -> Unit) {
    if (resource == null)
        StressLevelNotFound(modifier = Modifier.paddingHorizontalMedium(), onClick = onCreate)
    else
        MetricCardSide(
            modifier = Modifier.paddingHorizontalMedium(),
            icon = Drawables.Icons.Warning,
            title = Res.string.stress_level,
            text = resource.value,
            suffix = Res.string.level,
            description = resource.subtitle,
            content = {
                FivePackIndicator(resource.level, ::getBrushGradient)
            },
            color = Colors.Orange40,
            onClick = onClick
        )
}