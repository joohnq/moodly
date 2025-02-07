package com.joohnq.stress_level.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.FivePackIndicator
import com.joohnq.shared_resources.components.MetricCardSide
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.mapper.getBrushGradient
import com.joohnq.stress_level.ui.mapper.getTodayStressLevelRecord
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.ui.resource.StressLevelResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressLevelMetric(
    records: List<StressLevelRecordResource>,
    containerColor: Color = Colors.White,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    val record = records.getTodayStressLevelRecord()

    if (record == null)
        NotFoundHorizontal(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            title = Res.string.lets_set_up_daily_stress_level,
            subtitle = Res.string.add_new_journal,
            image = Drawables.Images.StressLevelManIllustration,
            onClick = onCreate
        )
    else
        MetricCardSide(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            icon = Drawables.Icons.Warning,
            title = stringResource(Res.string.stress_level),
            text = stringResource(record.stressLevel.value),
            suffix = stringResource(Res.string.level),
            description = stringResource(record.stressLevel.subtitle),
            content = {
                FivePackIndicator(record.stressLevel.level, ::getBrushGradient)
            },
            color = Colors.Orange40,
            onClick = onClick
        )
}

@Preview
@Composable
fun StressLevelMetricPreviewOne() {
    StressLevelMetric(
        records = listOf(
            StressLevelRecordResource(
                stressLevel = StressLevelResource.Five
            ),
        )
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewTwo() {
    StressLevelMetric(
        records = listOf(
            StressLevelRecordResource(
                stressLevel = StressLevelResource.Four
            ),
        )
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewThree() {
    StressLevelMetric(
        records = listOf(
            StressLevelRecordResource(
                stressLevel = StressLevelResource.Three
            ),
        )
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewFour() {
    StressLevelMetric(
        records = listOf(
            StressLevelRecordResource(
                stressLevel = StressLevelResource.Two
            ),
        )
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewFive() {
    StressLevelMetric(
        records = listOf(
            StressLevelRecordResource(
                stressLevel = StressLevelResource.One
            ),
        )
    )
}

@Preview
@Composable
fun StressLevelMetricPreviewNull() {
    StressLevelMetric(
        records = listOf()
    )
}
