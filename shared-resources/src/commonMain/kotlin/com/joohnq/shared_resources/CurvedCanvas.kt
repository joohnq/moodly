package com.joohnq.shared_resources

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

enum class CurvedCanvasPosition {
    TOP, BOTTOM
}

class ConcaveCanvas(private val inset: Dp) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        // o ponto de controle precisa ter duas vezes o tamanho
        // do inset relativo ao start e pontos finais
        // para assegurar que a curva tenha exatamente
        // a altura do valor do inset devido a natureza das curvas cúbicas
        val path = Path().apply {
            moveTo(0f, 0f)
            quadraticTo(size.width / 2, with(density) { inset.toPx() } * 2, size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}


class ConvexCanvas(private val offset: Dp, private val position: CurvedCanvasPosition) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val offsetPx = with(density) { offset.toPx() }
        val path = Path().apply {
            when (position) {
                CurvedCanvasPosition.TOP -> {
                    moveTo(0f, offsetPx)
                    quadraticTo(size.width / 2, -offsetPx, size.width, offsetPx)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                }

                CurvedCanvasPosition.BOTTOM -> {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height - offsetPx)
                    quadraticTo(size.width / 2, size.height + offsetPx, 0f, size.height - offsetPx)
                }
            }
            close()
        }
        return Outline.Generic(path)
    }
}


enum class ConcaveAnimation {
    In, Out
}

@Composable
fun AnimatedConcaveContentLayout(
    backgroundColor: Color,
    animation: ConcaveAnimation,
    content: @Composable () -> Unit,
) {
    val defaultInset = 50.dp
    val curvedInset = animateDpAsState(
        targetValue = when (animation) {
            ConcaveAnimation.In -> defaultInset
            ConcaveAnimation.Out -> 0.dp
        },
        animationSpec = tween(150, 0, LinearOutSlowInEasing)
    )

    ConcaveContentLayout(
        backgroundColor = backgroundColor,
        curvedInset = curvedInset.value,
        content = content
    )
}

@Composable
fun ConcaveContentLayout(
    backgroundColor: Color,
    curvedInset: Dp = 50.dp,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(
                shape = ConcaveCanvas(inset = curvedInset),
                color = backgroundColor
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(curvedInset)
        )
        content()
    }
}

@Composable
fun ConvexContentLayout(
    backgroundColor: Color,
    position: CurvedCanvasPosition = CurvedCanvasPosition.TOP,
    offset: Dp = 60.dp,
    content: @Composable () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .background(
                shape = ConvexCanvas(offset = offset, position = position),
                color = backgroundColor
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(offset)
        )
        content()
    }
}

