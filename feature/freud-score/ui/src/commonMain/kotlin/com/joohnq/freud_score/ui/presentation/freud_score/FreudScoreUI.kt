package com.joohnq.freud_score.ui.presentation.freud_score

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.freud_score.ui.resource.getAllFreudScoreResources
import com.joohnq.freud_score.ui.viewmodel.FreudScoreState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.BallItem
import com.joohnq.shared_resources.components.TextFieldWithLabelAndDoubleBorder
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.hyphen
import com.joohnq.shared_resources.out_of_100
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun DrawScope.drawDottedCircleBorder(
    color: Color,
    radius: Float,
    dotRadius: Float,
    numberOfDots: Int
) {
    val centerX = size.width / 2
    val centerY = size.height / 2

    for (i in 0 until numberOfDots) {
        val angle = (2 * PI * i) / numberOfDots
        val x = centerX + radius * cos(angle).toFloat()
        val y = centerY + radius * sin(angle).toFloat()

        drawCircle(
            color = color,
            radius = dotRadius,
            center = Offset(x, y)
        )
    }
}

fun DrawScope.drawFadedBorder(
    color: Color,
    borderWidth: Float
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(color, Color.Transparent),
        startY = 0f,
        endY = size.height
    )

    drawCircle(
        brush = gradientBrush,
        style = Stroke(width = borderWidth),
        radius = (size.minDimension / 2) - (borderWidth / 2)
    )
}

@Composable
fun DottedCircles(
    color: Color,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = Dimens.Shape.Circle
    Box(
        modifier = Modifier
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
            }
            .clip(shape)
    ) {
        Box(
            modifier = Modifier
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
                }
                .clip(shape)
        ) {
            Box(
                modifier = Modifier
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
                    }
                    .clip(shape)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(20.dp)
                        .background(color = Color.White, shape = shape)
                        .drawBehind {
                            drawFadedBorder(
                                color = color,
                                borderWidth = 10f
                            )
                        }
                        .clip(shape),
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

@Composable fun FreudScoreUI(
    state: FreudScoreState,
    onEvent: (FreudScoreEvent) -> Unit = {},
) {
    if (state.freudScore == null) return
    val resources = remember { getAllFreudScoreResources(state.freudScore.score) }

    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopBar(
                modifier = Modifier.paddingHorizontalMedium(),
                isDark = true,
                text = Res.string.freud_score,
                onGoBack = { onEvent(FreudScoreEvent.OnGoBack) },
            )
            Column(modifier = Modifier.paddingHorizontalMedium(), horizontalAlignment = Alignment.CenterHorizontally) {
                val color = state.freudScore.palette.backgroundColor

                DottedCircles(
                    color = color
                ) {
                    Text(
                        text = state.freudScore.score.toString(),
                        style = TextStyles.HeadingXlBold(),
                        color = state.freudScore.palette.backgroundColor
                    )
                    Text(
                        text = stringResource(Res.string.out_of_100),
                        style = TextStyles.TextSmRegular(),
                        color = Colors.Gray60
                    )
                }
                VerticalSpacer(24.dp)
                Text(
                    text = stringResource(state.freudScore.subtitle),
                    style = TextStyles.ParagraphLg(),
                    color = Colors.Gray80,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(12.dp)
                resources.forEachIndexed { i, resource ->
                    val portion = 100 / resources.size
                    val initial = (i + 1) * portion
                    BallItem(
                        color = resource.palette.backgroundColor,
                        title = stringResource(Res.string.hyphen, initial - portion, initial),
                        description = stringResource(resource.title),
                        isNotLast = i < resources.lastIndex
                    )
                }
            }
        }
    }
}

@Preview @Composable fun FreudScoreUIPreview() {
    FreudScoreUI(
        state = FreudScoreState(
            freudScore = FreudScoreResource.Unhealthy(20)
        ),
    )
}