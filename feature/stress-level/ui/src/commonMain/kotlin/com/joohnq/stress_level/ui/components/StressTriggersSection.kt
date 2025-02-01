package com.joohnq.stress_level.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toPercentage
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.ui.mapper.toResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressTriggersSection(
    modifier: Modifier = Modifier,
    records: List<StressLevelRecord>,
    onAddStressLevel: () -> Unit
) {
    val stressors = records
        .flatMap { it.stressors.toResource() }
        .groupingBy { it }
        .eachCount()
        .toList()
        .sortedByDescending { it.second }

    val totalOccurrences = stressors.size

    if (totalOccurrences < 1) return

    SectionHeader(
        modifier = modifier,
        title = Res.string.sleep_trigger,
        onSeeAll = {}
    )
    if (records.size < 3)
        NotFoundHorizontal(
            modifier = modifier,
            title = Res.string.you_dont_have_enough_sleep_records_yet,
            subtitle = Res.string.log_stress_level,
            image = Drawables.Images.StressTriggerIllustration,
            onClick = onAddStressLevel,
        )
    else {
        Card(
            modifier = modifier.fillMaxWidth(),
            colors = CardColors(
                containerColor = Colors.White,
                contentColor = Colors.Gray80,
                disabledContainerColor = Colors.White,
                disabledContentColor = Colors.Gray80
            )
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(
                                width = 1.dp,
                                color = Colors.Gray30,
                                shape = Dimens.Shape.Circle
                            )
                            .clip(Dimens.Shape.Circle),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.Outlined.Time),
                            contentDescription = null,
                            tint = Colors.Gray80,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(
                                width = 1.dp,
                                color = Colors.Gray30,
                                shape = Dimens.Shape.Circle
                            )
                            .clip(Dimens.Shape.Circle),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.Outlined.Upload),
                            contentDescription = null,
                            tint = Colors.Gray80,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                VerticalSpacer(16.dp)
                Text(
                    text = if (stressors.size == 1)
                        stringResource(stressors[0].first.text)
                    else
                        stringResource(
                            Res.string.stress_trigger_title,
                            stringResource(stressors[0].first.text),
                            stringResource(stressors[1].first.text)
                        ),
                    style = TextStyles.TextXlBold(),
                    color = Colors.Gray80
                )
                VerticalSpacer(8.dp)
                Text(
                    text = stringResource(Res.string.you_are_stressed_because_you_are_to_preoccupied_with),
                    style = TextStyles.ParagraphSm(),
                    color = Colors.Gray80
                )
                VerticalSpacer(16.dp)
                Column {
                    stressors.forEachIndexed { i, (stressor, count) ->
                        val percentage = (count.toDouble() / totalOccurrences) * 100
                        StressTrigger(
                            percentage = percentage.toPercentage(),
                            stressor = stressor
                        )
                        if (i < stressors.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.fillMaxWidth(),
                                color = Colors.Gray20
                            )
                        }
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
                stressors = listOf(Stressor.Work, Stressor.Kids)
            ),
            StressLevelRecord(
                stressors = listOf(Stressor.Finances, Stressor.Loneliness)
            )
        ),
        onAddStressLevel = {}
    )
}
