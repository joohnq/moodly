package com.joohnq.home.ui.components

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.joohnq.home.ui.event.DashboardBottomNavigationEvent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_health_journal
import com.joohnq.shared_resources.add_mood
import com.joohnq.shared_resources.components.BottomNavigationActionButton
import com.joohnq.shared_resources.components.BottomNavigationAddButton
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import kotlin.math.roundToInt

@Composable
fun AddButton(
    isExpanded: Boolean,
    onEvent: (DashboardBottomNavigationEvent) -> Unit,
) {
    val pxToMove = with(LocalDensity.current) {
        -40.dp.toPx().roundToInt()
    }
    val offset by animateIntOffsetAsState(
        targetValue = if (isExpanded) IntOffset(0, pxToMove) else IntOffset.Zero,
        label = "offset"
    )
    Row(
        modifier = Modifier.offset { offset }.padding(bottom = 80.dp)
            .background(color = Colors.Green50, shape = Dimens.Shape.Circle)
            .padding(if (isExpanded) 5.dp else 0.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (isExpanded)
            BottomNavigationActionButton(
                onClick = { onEvent(DashboardBottomNavigationEvent.AddMood) },
                drawable = Drawables.Icons.MoodNeutral,
                description = Res.string.add_mood
            )
        BottomNavigationAddButton(
            isExpanded = isExpanded,
            onClick = { onEvent(DashboardBottomNavigationEvent.ToggleExpanded) }
        )
        if (isExpanded)
            BottomNavigationActionButton(
                onClick = { onEvent(DashboardBottomNavigationEvent.AddHealthJournal) },
                drawable = Drawables.Icons.Document,
                description = Res.string.add_health_journal
            )
    }
}