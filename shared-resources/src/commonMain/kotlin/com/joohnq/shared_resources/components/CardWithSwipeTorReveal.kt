package com.joohnq.shared_resources.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.remove_journal
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.roundToInt

@Composable
fun CardWithSwipeTorReveal(
    content: @Composable (Shape, Modifier) -> Unit,
    secondaryContent: (@Composable () -> Unit)? = null,
    cardPadding: PaddingValues = PaddingValues(0.dp),
    onDelete: () -> Unit,
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
        if (secondaryContent != null) {
            secondaryContent()
        }
        Box(modifier = Modifier.padding(cardPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(IntrinsicSize.Max)
                    .align(Alignment.CenterEnd)
                    .onSizeChanged { contextButtonsWidth = it.width.toFloat() },
                verticalArrangement = Arrangement.Center
            ) {
                FilledIconButton(
                    onClick = {
                        onDelete()
                        isExpanded = false
                    },
                    modifier = Modifier.width(61.dp).fillMaxHeight(),
                    shape = Dimens.Shape.EndMedium,
                    colors = IconButtonColors(
                        containerColor = Colors.Orange50,
                        contentColor = Colors.White,
                        disabledContainerColor = Colors.Orange50,
                        disabledContentColor = Colors.White
                    )
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Trash),
                        contentDescription = stringResource(Res.string.remove_journal),
                        tint = Colors.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            content(
                if (!isExpanded) Dimens.Shape.Medium else Dimens.Shape.StartMedium,
                Modifier
                    .offset { IntOffset(offset.value.roundToInt(), 0) }
                    .pointerInput(contextButtonsWidth) {
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { _, dragAmount ->
                                scope.launch {
                                    val newOffset = (offset.value + dragAmount).coerceIn(
                                        -contextButtonsWidth,
                                        0f
                                    )
                                    offset.snapTo(newOffset)
                                }
                            },
                            onDragEnd = {
                                isExpanded = offset.value <= -contextButtonsWidth / 2f
                            }
                        )
                    }
            )
        }
    }
}