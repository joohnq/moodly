package com.joohnq.home.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.home.impl.ui.presentation.viewmodel.DashboardContract
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.self_journaling
import com.joohnq.shared_resources.sleep
import com.joohnq.shared_resources.stress_level
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.ui.entity.CentralAction
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DashboardCentral(
    padding: PaddingValues = PaddingValues(0.dp),
    onEvent: (DashboardContract.Event) -> Unit = {}
) {
    val items =
        listOf(
            CentralAction<Destination>(
                title = stringResource(Res.string.sleep),
                icon = Drawables.Icons.Outlined.Sleep,
                destination = Destination.App.AddSleepQuality
            ),
            CentralAction<Destination>(
                title = stringResource(Res.string.stress_level),
                icon = Drawables.Icons.Outlined.Warning,
                destination = Destination.App.AddStressLevel
            ),
            CentralAction<Destination>(
                title = stringResource(Res.string.self_journaling),
                icon = Drawables.Icons.Outlined.BookOpen,
                destination = Destination.App.AddSelfJournal
            ),
            CentralAction<Destination>(
                title = stringResource(Res.string.mood),
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
            horizontalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items.forEach { item ->
                CentralButton(
                    modifier = Modifier.weight(1f),
                    item = item,
                    onClick = { onEvent(DashboardContract.Event.OnNavigateTo(item.destination)) }
                )
            }
        }
    }
}