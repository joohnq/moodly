package com.joohnq.shared_resources.components.modifier

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.launch

@Composable
fun Modifier.swipeToReveal(
    width: Float,
    offset: Animatable<Float, AnimationVector1D>,
    setIsExpanded: (Boolean) -> Unit,
): Modifier {
    val scope = rememberCoroutineScope()

    return pointerInput(width) {
        detectHorizontalDragGestures(
            onHorizontalDrag = { _, dragAmount ->
                scope.launch {
                    val newOffset = (offset.value + dragAmount).coerceIn(
                        -width,
                        0f
                    )
                    offset.snapTo(newOffset)
                }
            },
            onDragEnd = {
                setIsExpanded(offset.value <= -width / 2f)
            }
        )
    }
}