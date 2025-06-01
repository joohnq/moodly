package com.joohnq.shared_resources.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun SwipeTorRevealCard(
    modifier: Modifier = Modifier,
    onAction: () -> Unit,
    padding: PaddingValues = PaddingValues(0.dp),
    secondary: @Composable () -> Unit = {},
    content: @Composable (Modifier) -> Unit,
) {
    var buttonsWidth by remember { mutableFloatStateOf(0f) }
    val offset = remember { Animatable(initialValue = 0f) }
    var isExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(isExpanded) {
        offset.animateTo(if (isExpanded) -buttonsWidth else 0f)
    }

    Row(modifier = modifier.height(intrinsicSize = IntrinsicSize.Max)) {
        secondary()
        Box(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(IntrinsicSize.Max)
                    .align(Alignment.CenterEnd)
                    .onSizeChanged { buttonsWidth = it.width.toFloat() },
                verticalArrangement = Arrangement.Center
            ) {
                DeleteButton {
                    onAction()
                    isExpanded = false
                }
            }
            content(
                Modifier
                    .offset { IntOffset(offset.value.roundToInt(), 0) }
                    .swipeToReveal(
                        width = buttonsWidth,
                        offset = offset,
                        setIsExpanded = { isExpanded = it }
                    )
            )
        }
    }
}