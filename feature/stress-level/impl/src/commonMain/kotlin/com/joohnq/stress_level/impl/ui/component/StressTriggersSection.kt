package com.joohnq.stress_level.impl.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.DoubleMapper.toPercentage
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ColoredIndicatorItem
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.log_stress_level
import com.joohnq.shared_resources.stress_trigger
import com.joohnq.shared_resources.stress_trigger_title
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.you_are_stressed_because_you_are_to_preoccupied_with
import com.joohnq.shared_resources.you_dont_have_enough_sleep_records_yet
import com.joohnq.stress_level.impl.ui.mapper.StressorResourceMapper.toMap
import com.joohnq.stress_level.impl.ui.mapper.StressorResourceMapper.toSegments
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressTriggersSection(
    modifier: Modifier = Modifier,
    records: List<StressLevelRecordResource>,
    onAddStressLevel: () -> Unit = {},
) {
    val stressors = records.flatMap { it.stressors }
    val stressorsMap = stressors.toMap()
    val segments = stressorsMap.toSegments(stressors.size)

    SectionHeader(
        modifier = modifier,
        title = Res.string.stress_trigger
    )
    if (stressors.size < 3) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.you_dont_have_enough_sleep_records_yet,
            subtitle = Res.string.log_stress_level,
            image = Drawables.Images.StressLevelTrigger,
            onClick = onAddStressLevel
        )
    } else {
        Card(
            modifier = modifier.fillMaxWidth(),
            colors =
                CardColors(
                    containerColor = Colors.Gray5,
                    contentColor = Colors.Gray80,
                    disabledContainerColor = Colors.Gray5,
                    disabledContentColor = Colors.Gray80
                )
        ) {
            Column(
                modifier = Modifier.paddingAllSmall(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TriggerIcon(
                        icon = Drawables.Icons.Outlined.Time
                    )
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        MultiColorCircularProgress(
                            segments = segments,
                            strokeWidth = 10.dp,
                            modifier = Modifier.size(150.dp)
                        )
                        TriggerIcon(
                            modifier = Modifier.scale(1.4f),
                            icon = stressorsMap.first().first.icon
                        )
                    }
                    TriggerIcon(
                        icon = Drawables.Icons.Outlined.Upload
                    )
                }
                VerticalSpacer(16.dp)
                Text(
                    text =
                        if (stressors.size == 1) {
                            stringResource(stressorsMap[0].first.text)
                        } else {
                            stringResource(
                                Res.string.stress_trigger_title,
                                stringResource(stressorsMap[0].first.text),
                                stringResource(stressorsMap[1].first.text)
                            )
                        },
                    style = TextStyles.textXlBold(),
                    color = Colors.Gray80
                )
                VerticalSpacer(8.dp)
                Text(
                    text = stringResource(Res.string.you_are_stressed_because_you_are_to_preoccupied_with),
                    style = TextStyles.paragraphSm(),
                    color = Colors.Gray80,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(16.dp)
                Column {
                    stressorsMap.forEachIndexed { i, (stressor, count) ->
                        val percentage = (count.toDouble() / stressors.size) * 100
                        ColoredIndicatorItem(
                            color = stressor.color,
                            title = stringResource(stressor.text),
                            description = percentage.toPercentage(),
                            isNotLast = i != stressorsMap.lastIndex
                        )
                    }
                }
            }
        }
    }
}
