package com.joohnq.sleep_quality.ui.presentation.sleep_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.you_slept_for
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.sleep_quality.ui.presentation.sleep_history.event.SleepHistoryEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityState
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepQualityHistoryCard(sleepQuality: SleepQualityRecord) {
    val resource = sleepQuality.sleepQuality.toResource()
    val duration =
        DatetimeProvider.getDuration(sleepQuality.endSleeping, sleepQuality.startSleeping)
    val durationInString = DatetimeProvider.formatTimeHMin(duration)

    Card(
        modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
        colors = ComponentColors.Card.MainCardColors(),
        shape = Dimens.Shape.Medium,
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = Colors.White, shape = Dimens.Shape.ExtraSmall),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = DatetimeProvider.getMonthAbbreviatedName(sleepQuality.createdAt),
                    style = TextStyles.LabelSm(),
                    color = Colors.Brown100Alpha64
                )
                Text(
                    text = sleepQuality.createdAt.dayOfMonth.toString(),
                    style = TextStyles.TextLgExtraBold(),
                    color = Colors.Brown80
                )
            }
            HorizontalSpacer(20.dp)
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.you_slept_for, durationInString),
                    style = TextStyles.TextMdBold(),
                    color = Colors.Brown80
                )
            }
            HorizontalSpacer(20.dp)
        }
    }
}

@Composable
fun SleepHistoryUI(
    state: SleepQualityState,
    onEvent: (SleepHistoryEvent) -> Unit,
) {
    state.sleepQualityRecords.foldComposable(
        onSuccess = { sleepQualities ->
            Scaffold {
                LazyColumn {
                    items(sleepQualities) { sleepQuality ->
                        SleepQualityHistoryCard(sleepQuality = sleepQuality)
                    }
                }
            }
        }
    )
}