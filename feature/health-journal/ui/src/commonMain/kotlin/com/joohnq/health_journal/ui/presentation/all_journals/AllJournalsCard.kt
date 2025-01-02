package com.joohnq.health_journal.ui.presentation.all_journals

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
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
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.mood.ui.MoodResource.Companion.toResource
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.shared.domain.DatetimeProvider
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.components.TextEllipsis
import com.joohnq.shared.ui.components.TextWithBackground
import com.joohnq.shared.ui.components.VerticalSpacer
import com.joohnq.shared.ui.hour
import com.joohnq.shared.ui.remove_journal
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.ComponentColors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.theme.TextStyles
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
    val offset = remember {
        Animatable(initialValue = 0f)
    }
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

@Composable
fun AllJournalsCard(
    i: Int,
    lastIndex: Int,
    healthJournal: HealthJournalRecord,
    onEvent: (AllJournalEvent) -> Unit,
    onDelete: () -> Unit,
) {
    val mood = healthJournal.mood
    val resource = mood.toResource()
    val palette = resource.palette

    CardWithSwipeTorReveal(
        content = { shape, modifier ->
            Card(
                shape = shape,
                modifier = modifier,
                colors = ComponentColors.Card.MainCardColors(),
                onClick = {
                    onEvent(
                        AllJournalEvent.OnSelectJournal(
                            healthJournal.id
                        )
                    )
                }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = palette.color,
                                    shape = Dimens.Shape.Circle
                                )
                                .size(44.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            MoodFace(
                                modifier = Modifier.size(20.dp),
                                mood = resource,
                                backgroundColor = Colors.White,
                                color = palette.barFaceColor
                            )
                        }
                        TextWithBackground(
                            text = stringResource(resource.text),
                            backgroundColor = palette.backgroundColor,
                            textColor = palette.color
                        )
                    }
                    VerticalSpacer(10.dp)
                    TextEllipsis(
                        text = healthJournal.title,
                        style = TextStyles.TextMdExtraBold(),
                        color = Colors.Brown80,
                        maxLines = 1
                    )
                    VerticalSpacer(10.dp)
                    TextEllipsis(
                        text = healthJournal.description,
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown100Alpha64,
                        maxLines = 2
                    )
                }
            }
        },
        secondaryContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.width(3.dp).weight(1f).fillMaxHeight()
                        .background(color = if (i != 0) Colors.Brown80 else Colors.Transparent)
                )
                Column(
                    modifier = Modifier.size(50.dp)
                        .background(
                            color = Colors.Brown80,
                            shape = Dimens.Shape.ExtraSmall
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Clock),
                        contentDescription = stringResource(Res.string.hour),
                        tint = Colors.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = DatetimeProvider.formatTime(healthJournal.date),
                        style = TextStyles.TextSmSemiBold(),
                        color = Colors.White
                    )
                }
                Box(
                    modifier = Modifier.width(3.dp).weight(1f).fillMaxHeight()
                        .background(color = if (i != lastIndex) Colors.Brown80 else Colors.Transparent)
                )
            }
        },
        cardPadding = PaddingValues(end = 10.dp, top = 5.dp, bottom = 5.dp),
        onDelete = onDelete
    )
}