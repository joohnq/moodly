package com.joohnq.moodapp.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.util.helper.DatetimeManager
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.TextStyles

@Composable
fun MoodBarStatistic(
    statsRecords: List<StatsRecord>,
    currentStatsRecord: StatsRecord,
    onClick: (StatsRecord) -> Unit = {}
) {
    val height = 250.dp
    val proportion = height / 100
    val pagerState = rememberPagerState(pageCount = {
        statsRecords.size
    })

    val selectedIndex = statsRecords.indexOf(currentStatsRecord)

    LaunchedEffect(selectedIndex) {
        if (selectedIndex >= 0) {
            pagerState.animateScrollToPage(selectedIndex)
        }
    }
    val textHeight = calculateTextHeight(font = TextStyles.TextSmSemiBold())

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().height(height + textHeight)
    ) {
        val usableWidth = maxWidth - (15.dp * 8)
        val boxWidth = usableWidth / 7

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    repeat(5) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(height / 5).dashedLine(
                                color = Colors.Brown20,
                            )
                        )
                    }
                }
            }
            HorizontalPager(
                contentPadding = PaddingValues(horizontal = 15.dp),
                state = pagerState,
                pageSize = PageSize.Fixed(boxWidth),
                pageSpacing = 15.dp,
                verticalAlignment = Alignment.Bottom,
                beyondViewportPageCount = 3,
                userScrollEnabled = true,
                snapPosition = SnapPosition.Center,
            ) { page ->
                val statsRecord = statsRecords[page]
                val boxHeight = statsRecord.mood.healthLevel.times(proportion)
                val background = when {
                    currentStatsRecord == statsRecord -> statsRecord.mood.palette.barColor
                    else -> Colors.Brown20
                }
                val barFaceColor = when {
                    currentStatsRecord == statsRecord -> statsRecord.mood.palette.barFaceColor
                    else -> Colors.Brown40
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.width(boxWidth).fillMaxHeight()
                ) {
                    Button(
                        modifier = Modifier
                            .width(boxWidth)
                            .height(boxHeight),
                        contentPadding = PaddingValues(5.dp),
                        colors = ButtonColors(
                            containerColor = background,
                            disabledContainerColor = background,
                            contentColor = background,
                            disabledContentColor = background,
                        ),
                        shape = RoundedCornerShape(topEnd = 100.dp, topStart = 100.dp),
                        onClick = { onClick(statsRecord) }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                        ) {
                            MoodFace(
                                modifier = Modifier.size(boxWidth - 10.dp),
                                mood = statsRecord.mood,
                                backgroundColor = barFaceColor,
                                color = background
                            )
                        }
                    }
                    Text(
                        text = DatetimeManager.formatShortDate(statsRecord.date.date),
                        style = TextStyles.TextSmSemiBold(),
                        color = Colors.Brown100Alpha64,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Preview @Composable fun MoodBarStatisticPreview() {
    MoodBarStatistic(
        listOf(
            StatsRecord.init().copy(mood = Mood.Depressed),
        ),
        currentStatsRecord = StatsRecord.init().copy(mood = Mood.Depressed)
    )
}

@Preview @Composable fun MoodBarStatisticPreview2() {
    MoodBarStatistic(
        listOf(
            StatsRecord.init().copy(mood = Mood.Neutral),
            StatsRecord.init().copy(mood = Mood.Sad),
            StatsRecord.init().copy(mood = Mood.Overjoyed),
            StatsRecord.init().copy(mood = Mood.Depressed),
        ),
        currentStatsRecord = StatsRecord.init().copy(mood = Mood.Sad)
    )
}

