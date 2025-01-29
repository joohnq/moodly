package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toFormattedDateString
import com.joohnq.core.ui.mapper.toFormattedTimeString
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.*
import com.joohnq.shared_resources.end_sleeping
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.sleep_quality
import com.joohnq.shared_resources.sleep_quality_level
import com.joohnq.shared_resources.sleeping_influences
import com.joohnq.shared_resources.start_sleeping
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.to_word
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.entity.SleepStatsItem
import com.joohnq.sleep_quality.ui.mapper.toMoodResource
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.toSleepQualityEvent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SleepQualityUI(
    record: SleepQualityRecord,
    onEvent: (SleepQualityEvent) -> Unit = {},
) {
    val sleepQuality = record.sleepQuality.toResource()
    val sleepInfluences = record.sleepInfluences.toResource()
    val mood = record.sleepQuality.toResource().toMoodResource()
    val options = remember {
        listOf(
            SleepStatsItem(
                icon = Drawables.Icons.MoodNeutral,
                title = Res.string.mood,
            ) {
                Text(
                    text = stringResource(mood.text),
                    style = TextStyles.TextMdSemiBold(),
                    color = Colors.Brown80
                )
            },
            SleepStatsItem(
                icon = Drawables.Icons.Moon,
                title = Res.string.sleeping_influences,
            ) {
                if (sleepInfluences.isNotEmpty())
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        sleepInfluences.forEach { sleepInfluences ->
                            TextWithBackground(
                                text = stringResource(sleepInfluences.title),
                                backgroundColor = Colors.Green50,
                                textColor = Colors.White
                            )
                        }
                    }
            }
        )
    }

    SharedPanelComponent(
        containerColor = Colors.Brown10,
        isDark = false,
        paddingValues = Dimens.Padding.HorizontalMedium,
        backgroundColor = mood.palette.color,
        image = Drawables.Images.SleepQualityBackground,
        title = Res.string.sleep_quality,
        color = mood.palette.imageColor,
        onEvent = { event ->
            onEvent(event.toSleepQualityEvent())
        },
        topBar = {
            TextWithBackground(
                text = record.createdAt.toFormattedDateString(),
                textColor = sleepQuality.palette.color,
                backgroundColor = sleepQuality.palette.secondaryBackgroundColor,
            )
        },
        panel = {
            Column(
                modifier = Modifier
                    .paddingHorizontalMedium()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        alignment = Alignment.CenterHorizontally
                    )
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Sleep),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = mood.palette.backgroundColor
                    )
                    Text(
                        text = stringResource(
                            Res.string.sleep_quality_level,
                            record.sleepQuality.level
                        ),
                        style = TextStyles.Heading2xlExtraBold(),
                        color = Colors.White
                    )
                }
                VerticalSpacer(10.dp)
                Text(
                    text = stringResource(sleepQuality.firstText),
                    style = TextStyles.HeadingSmExtraBold(),
                    color = Colors.White
                )
                VerticalSpacer(20.dp)
                Row(
                    modifier = Modifier
                        .background(color = Colors.White, shape = Dimens.Shape.Circle)
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(30.dp)
                            .background(
                                color = Colors.Brown10,
                                shape = Dimens.Shape.Circle
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.Sleep),
                            contentDescription = stringResource(Res.string.start_sleeping),
                            tint = Colors.Brown80,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    HorizontalSpacer(10.dp)
                    Text(
                        text = record.startSleeping.toFormattedTimeString(),
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown80
                    )
                    Text(
                        text = stringResource(Res.string.to_word),
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown100Alpha64,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                    Box(
                        modifier = Modifier.size(30.dp)
                            .background(
                                color = Colors.Brown10,
                                shape = Dimens.Shape.Circle
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.Sun),
                            contentDescription = stringResource(Res.string.end_sleeping),
                            tint = Colors.Brown80,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    HorizontalSpacer(10.dp)
                    Text(
                        text = record.endSleeping.toFormattedTimeString(),
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown80
                    )
                }
            }
        },
        content = {
            FlowRow(
                maxItemsInEachRow = 1,
                maxLines = 2,
                modifier = Modifier.fillMaxSize().wrapContentHeight()
                    .paddingHorizontalMedium(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                options.forEach { item ->
                    SleepQualityCard(
                        modifier = Modifier.weight(1f),
                        item = item
                    )
                }
            }
        }
    )
}
