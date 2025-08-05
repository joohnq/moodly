package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.card.MetricSummaryCard
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.lets_set_up_daily_stress_level
import com.joohnq.shared_resources.level
import com.joohnq.shared_resources.stress
import com.joohnq.shared_resources.stress_level
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.getTodayStressLevelRecord
import com.joohnq.stress_level.impl.ui.mapper.StressLevelResourceMapper.getBrushGradient
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelMetric(
    records: List<StressLevelRecordResource>,
    containerColor: Color = Colors.White,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val record = records.getTodayStressLevelRecord()

    SectionHeader(
        modifier = Modifier.paddingHorizontalMedium(),
        title = Res.string.stress,
        onSeeMore = onClick
    )
    if (record == null) {
        NotFoundHorizontalLayout(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            title = Res.string.lets_set_up_daily_stress_level,
            subtitle = Res.string.add_new_journal,
            image = Drawables.Images.StressLevelHistory,
            onClick = onCreate
        )
    } else {
        MetricSummaryCard(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            icon = Drawables.Icons.Filled.Warning,
            title = stringResource(Res.string.stress_level),
            text = stringResource(record.stressLevel.value),
            suffix = stringResource(Res.string.level),
            description = stringResource(record.stressLevel.subtitle),
            content = {
                StressLevelRatingBar(record.stressLevel.level, ::getBrushGradient)
            },
            color = Colors.Orange40,
            onClick = onClick
        )
    }
}