package com.joohnq.shared_resources.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.swipeToReveal
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeTorRevealCard(
    secondaryContent: (@Composable () -> Unit)? = null,
    padding: PaddingValues = PaddingValues(0.dp),
    onAction: () -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var contextButtonsWidth by remember { mutableFloatStateOf(0f) }
    val offset = remember { Animatable(initialValue = 0f) }
    var isExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(isExpanded) {
        scope.launch {
            offset.animateTo(if (isExpanded) -contextButtonsWidth else 0f)
        }
    }

    Row(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max)) {
        secondaryContent?.let { it() }
        Box(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(IntrinsicSize.Max)
                    .align(Alignment.CenterEnd)
                    .onSizeChanged { contextButtonsWidth = it.width.toFloat() },
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
                        width = contextButtonsWidth,
                        offset = offset,
                        setIsExpanded = { isExpanded = it }
                    )
            )
        }
    }
}

@Composable
fun SwipeTorRevealCard(
    modifier: Modifier = Modifier,
    onAction: () -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var buttonsWidth by remember { mutableFloatStateOf(0f) }
    val offset = remember { Animatable(initialValue = 0f) }
    var isExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(isExpanded) {
        scope.launch {
            offset.animateTo(if (isExpanded) -buttonsWidth else 0f)
        }
    }

    Row(modifier = modifier.height(intrinsicSize = IntrinsicSize.Max)) {
        Box(modifier = Modifier) {
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