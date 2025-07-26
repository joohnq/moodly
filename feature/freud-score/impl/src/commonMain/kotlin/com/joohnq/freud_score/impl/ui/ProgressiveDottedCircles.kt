package com.joohnq.freud_score.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.draw_scope.drawFadedBorder
import com.joohnq.shared_resources.components.modifier.drawDottedCircleBorder
import com.joohnq.shared_resources.theme.Dimens

@Composable
fun ProgressiveDottedCircles(
    modifier: Modifier = Modifier,
    color: Color,
    content: @Composable ColumnScope.() -> Unit,
) {
    val shape = Dimens.Shape.Circle
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(16.dp)
                .drawBehind {
                    drawDottedCircleBorder(
                        color = color.copy(alpha = 0.3f),
                        radius = size.minDimension / 2 - 10,
                        dotRadius = 13f,
                        numberOfDots = 40
                    )
                }.clip(shape)
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(20.dp)
                    .drawBehind {
                        drawDottedCircleBorder(
                            color = color.copy(alpha = 0.6f),
                            radius = size.minDimension / 2 - 10,
                            dotRadius = 10f,
                            numberOfDots = 40
                        )
                    }.clip(shape)
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(20.dp)
                        .drawBehind {
                            drawDottedCircleBorder(
                                color = color,
                                radius = size.minDimension / 2 - 10,
                                dotRadius = 7f,
                                numberOfDots = 40
                            )
                        }.clip(shape)
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .padding(20.dp)
                            .background(color = Color.White, shape = shape)
                            .drawBehind {
                                drawFadedBorder(
                                    color = color,
                                    borderWidth = 10f
                                )
                            }.clip(shape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        content()
                    }
                }
            }
        }
    }
}
