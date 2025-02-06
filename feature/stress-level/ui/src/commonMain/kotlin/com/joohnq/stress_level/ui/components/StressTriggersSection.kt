package com.joohnq.stress_level.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toPercentage
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.BallItem
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.ui.mapper.toResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressTriggersSection(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.White,
    records: List<StressLevelRecord>,
    onAddStressLevel: () -> Unit
) {
    val stressors = records.flatMap { it.stressors.toResource() }

    val stressorsMap = stressors
        .groupingBy { it }
        .eachCount()
        .toList()
        .sortedByDescending { it.second }

    val segments = stressorsMap.map { (stressor, count) ->
        val percent = (count.toDouble() / stressors.size) * 100
        stressor.color to percent.toFloat()
    }

    SectionHeader(
        modifier = modifier,
        title = Res.string.sleep_trigger,
        onSeeAll = {}
    )
    if (stressors.size < 3)
        NotFoundHorizontal(
            modifier = modifier,
            containerColor = containerColor,
            title = Res.string.you_dont_have_enough_sleep_records_yet,
            subtitle = Res.string.log_stress_level,
            image = Drawables.Images.StressTriggerIllustration,
            onClick = onAddStressLevel,
        )
    else {
        Card(
            modifier = modifier.fillMaxWidth(),
            colors = CardColors(
                containerColor = containerColor,
                contentColor = Colors.Gray80,
                disabledContainerColor = containerColor,
                disabledContentColor = Colors.Gray80
            )
        ) {
            Column(modifier = Modifier.paddingAllSmall(), horizontalAlignment = Alignment.CenterHorizontally) {
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
                    text = if (stressors.size == 1)
                        stringResource(stressorsMap[0].first.text)
                    else
                        stringResource(
                            Res.string.stress_trigger_title,
                            stringResource(stressorsMap[0].first.text),
                            stringResource(stressorsMap[1].first.text)
                        ),
                    style = TextStyles.TextXlBold(),
                    color = Colors.Gray80
                )
                VerticalSpacer(8.dp)
                Text(
                    text = stringResource(Res.string.you_are_stressed_because_you_are_to_preoccupied_with),
                    style = TextStyles.ParagraphSm(),
                    color = Colors.Gray80,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(16.dp)
                Column {
                    stressorsMap.forEachIndexed { i, (stressor, count) ->
                        val percentage = (count.toDouble() / stressors.size) * 100
                        BallItem(
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

@Preview
@Composable
fun StressTriggersSectionPreview() {
    StressTriggersSection(
        records = listOf(
            StressLevelRecord(
                stressors = listOf(Stressor.Work)
            ),
            StressLevelRecord(
                stressors = listOf(Stressor.Work, Stressor.Kids, Stressor.Relationship)
            ),
            StressLevelRecord(
                stressors = listOf(Stressor.Finances, Stressor.Loneliness)
            )
        ),
        onAddStressLevel = {}
    )
}

@Preview
@Composable
fun StressTriggersSectionPreviewEmpty() {
    StressTriggersSection(
        records = emptyList(),
        onAddStressLevel = {}
    )
}