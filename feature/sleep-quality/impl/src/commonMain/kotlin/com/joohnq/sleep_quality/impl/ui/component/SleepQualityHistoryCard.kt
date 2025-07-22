package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.calculateDuration
import com.joohnq.api.mapper.toAbbreviatedMonthName
import com.joohnq.api.mapper.toHoursAndMinutesString
import com.joohnq.mood.impl.ui.components.MoodFace
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.no_sleep_influences
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.impl.ui.mapper.toMoodResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepQualityHistoryCard(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    record: SleepQualityRecordResource,
) {
    val duration = calculateDuration(
        start = record.startSleeping,
        end = record.endSleeping
    )
    val resource = record.sleepQuality.toMoodResource()

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Color.Unspecified,
            disabledContainerColor = containerColor,
            disabledContentColor = Color.Unspecified
        ),
        shape = Dimens.Shape.Medium,
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .paddingAllSmall(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .background(color = Colors.Brown10, shape = Dimens.Shape.Medium)
                    .padding(horizontal = 15.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = record.createdAt.toAbbreviatedMonthName(),
                    style = TextStyles.LabelSm(),
                    color = Colors.Brown40
                )
                Text(
                    text = record.createdAt.dayOfMonth.toString(),
                    style = TextStyles.TextXlExtraBold(),
                    color = Colors.Brown80
                )
            }
            HorizontalSpacer(20.dp)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = duration.toHoursAndMinutesString(),
                        style = TextStyles.TextMdBold(),
                        color = Colors.Brown80
                    )
                    Row(
                        modifier = Modifier
                            .background(color = resource.palette.color, shape = Dimens.Shape.Circle)
                            .padding(horizontal = 7.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        MoodFace(
                            modifier = Modifier.size(16.dp),
                            resource = resource,
                            backgroundColor = Colors.White,
                            color = resource.palette.color
                        )
                        Text(
                            text = stringResource(resource.text),
                            style = TextStyles.TextSmBold(),
                            color = Colors.White
                        )
                    }
                }
                Text(
                    text =
                        if (record.sleepInfluences.isNotEmpty())
                            record.sleepInfluences.joinToString(", ")
                        else
                            stringResource(Res.string.no_sleep_influences),
                    style = TextStyles.TextSmMedium(),
                    color = Colors.Brown80,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun SleepQualityHistoryCardPreview() {
    SleepQualityHistoryCard(
        record = SleepQualityRecordResource(),
    )
}