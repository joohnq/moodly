package com.joohnq.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.CentralAction
import com.joohnq.home.ui.presentation.dashboard.event.DashboardEvent
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DashboardCentral(
    padding: PaddingValues,
    onEvent: (DashboardEvent) -> Unit
) {
    val items =
        listOf<CentralAction<Destination>>(
            CentralAction<Destination>(
                title = Res.string.sleep,
                icon = Drawables.Icons.Sleep,
                destination = Destination.App.AddSleepQuality
            ),
            CentralAction<Destination>(
                title = Res.string.stress_level,
                icon = Drawables.Icons.Outlined.Warning,
                destination = Destination.App.AddStressLevel
            ),
            CentralAction<Destination>(
                title = Res.string.self_journaling,
                icon = Drawables.Icons.Outlined.BookOpen,
                destination = Destination.App.AddSelfJournal
            ),
            CentralAction<Destination>(
                title = Res.string.mood,
                icon = Drawables.Icons.Outlined.MoodNeutral,
                destination = Destination.App.AddMood
            )
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.Black48)
            .padding(bottom = padding.calculateBottomPadding() + 10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .paddingHorizontalMedium()
                .background(color = Colors.White, shape = Dimens.Shape.Large)
                .clip(Dimens.Shape.Large)
                .paddingAllSmall(),
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items.forEach { item ->
                CentralButton(
                    modifier = Modifier.weight(1f),
                    item = item,
                    onClick = { onEvent(DashboardEvent.OnNavigateTo(item.destination)) }
                )
            }
        }
    }
}