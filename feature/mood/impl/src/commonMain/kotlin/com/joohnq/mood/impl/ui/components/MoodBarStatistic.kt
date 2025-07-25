package com.joohnq.mood.impl.ui.components

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.joohnq.api.mapper.toShortDateString
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.components.text.CalculateTextHeight
import com.joohnq.shared_resources.components.dashedLine
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun MoodBarStatistic(
    records: List<MoodRecordResource>,
    height: Dp = 250.dp,
) {
    val current = records.last()
    val proportion = height / 100
    val pagerState = rememberPagerState(pageCount = {
        records.size
    })

    val selectedIndex = records.indexOf(current)

    LaunchedEffect(selectedIndex) {
        if (selectedIndex >= 0) {
            pagerState.animateScrollToPage(selectedIndex)
        }
    }
    val textHeight = CalculateTextHeight(font = TextStyles.textSmSemiBold())
    val resource = current.mood

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().height(height + textHeight)
    ) {
        val usableWidth = maxWidth - (15.dp * 8)
        val boxWidth = usableWidth / 7

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
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
                val record = records[page]
                val boxHeight = record.mood.healthLevel.times(proportion)
                val barFaceColor = when {
                    current == record -> resource.palette.barFaceColor
                    else -> Colors.Brown40
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.width(boxWidth).fillMaxHeight()
                ) {
                    Card(
                        modifier = Modifier
                            .width(boxWidth)
                            .height(boxHeight),
                        colors = CardColors(
                            containerColor = barFaceColor,
                            disabledContainerColor = barFaceColor,
                            contentColor = barFaceColor,
                            disabledContentColor = barFaceColor,
                        ),
                        shape = RoundedCornerShape(topEnd = 100.dp, topStart = 100.dp),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize().padding(5.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            MoodFace(
                                modifier = Modifier.size(boxWidth - 10.dp),
                                resource = record.mood,
                                backgroundColor = Colors.White,
                                color = barFaceColor
                            )
                        }
                    }
                    Text(
                        text = record.createdAt.date.toShortDateString(),
                        style = TextStyles.textSmSemiBold(),
                        color = Colors.Brown100Alpha64,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}