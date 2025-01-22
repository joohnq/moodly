package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.components.SharedPanelComponent
import com.joohnq.shared_resources.components.SleepQualityCard
import com.joohnq.shared_resources.components.TextWithBackground
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.end_sleeping
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.sleep_quality
import com.joohnq.shared_resources.sleep_quality_level
import com.joohnq.shared_resources.sleep_stats
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
import com.joohnq.sleep_quality.ui.mapper.toMood
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.splash.ui.presentation.splash_screen.SplashScreenUI
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SleepQualityUI(
    sleepQualityRecords: UiState<List<SleepQualityRecord>>,
    onEvent: (SleepQualityEvent) -> Unit = {},
) {
    sleepQualityRecords.foldComposable(
        onLoading = { SplashScreenUI() },
        onSuccess = { sleepQualityRecords ->
            val selected = sleepQualityRecords.first()
            val sleepQuality = selected.sleepQuality.toResource()
            val sleepInfluences = selected.sleepInfluences.toResource()
            val mood = selected.sleepQuality.toResource().toMood()
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
                onGoBack = { onEvent(SleepQualityEvent.GoBack) },
                backgroundColor = mood.palette.color,
                backgroundImage = Drawables.Images.SleepQualityBackground,
                panelTitle = Res.string.sleep_quality,
                bodyTitle = Res.string.sleep_stats,
                color = mood.palette.backgroundColor,
                onAdd = { onEvent(SleepQualityEvent.Add) },
                topBarContent = {
                    TextWithBackground(
                        text = DatetimeProvider.formatDate(selected.createdAt.date),
                        textColor = sleepQuality.palette.color,
                        backgroundColor = sleepQuality.palette.secondaryBackgroundColor,
                    )
                },
                panelContent = {
                    Column(
                        modifier = Modifier
                            .paddingHorizontalMedium()
                            .padding(top = it.calculateTopPadding())
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(
                                Res.string.sleep_quality_level,
                                selected.sleepQuality.level
                            ),
                            style = TextStyles.Heading2xlExtraBold(),
                            color = Colors.White
                        )
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
                                text = selected.startSleeping,
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
                                text = selected.endSleeping,
                                style = TextStyles.TextMdSemiBold(),
                                color = Colors.Brown80
                            )
                        }
                    }
                },
                content = {
                    item {
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
                }
            )
        }
    )
}