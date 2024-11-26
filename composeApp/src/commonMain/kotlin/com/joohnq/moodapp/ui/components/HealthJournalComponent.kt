package com.joohnq.moodapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.util.helper.DatetimeManager
import com.joohnq.moodapp.util.helper.StatsManager
import com.joohnq.moodapp.util.mappers.forEachMapComposable
import com.joohnq.moodapp.util.mappers.items
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.friday_char
import moodapp.composeapp.generated.resources.monday_char
import moodapp.composeapp.generated.resources.saturday_char
import moodapp.composeapp.generated.resources.sunday_char
import moodapp.composeapp.generated.resources.thursday_char
import moodapp.composeapp.generated.resources.tuesday_char
import moodapp.composeapp.generated.resources.wednesday_char
import org.jetbrains.compose.resources.stringResource

@Composable
fun HealthJournalComponent(
    modifier: Modifier = Modifier,
    healthJournals: List<HealthJournalRecord>
) {
    val dayOfWeek =
        remember { DatetimeManager.getCurrentWeekDay(DatetimeManager.getCurrentDateTime()) }
    val healthJournalsMap =
        remember { StatsManager.getHealthJournal(healthJournals = healthJournals) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp, alignment = Alignment.CenterVertically)
        ) {
            items(dayOfWeek + 1) {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(
                            color = Colors.Transparent,
                            shape = Dimens.Shape.ExtraExtraSmall
                        )
                        .size(18.dp),
                )
            }
            items(healthJournalsMap) { statsRecords ->
                val background = when {
                    statsRecords?.size == 1 -> Colors.Purple10
                    statsRecords?.size != null && statsRecords.size >= 2 -> Colors.White
                    else -> Colors.Purple20
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(color = background, shape = Dimens.Shape.ExtraExtraSmall)
                        .size(18.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HealthJournalComponentColorful(
    modifier: Modifier = Modifier,
    healthJournals: List<HealthJournalRecord>
) {
    val dayOfWeek = DatetimeManager.getCurrentWeekDay(DatetimeManager.getCurrentDateTime())
    val weekChars = remember {
        listOf(
            Res.string.sunday_char,
            Res.string.monday_char,
            Res.string.tuesday_char,
            Res.string.wednesday_char,
            Res.string.thursday_char,
            Res.string.friday_char,
            Res.string.saturday_char,
        )
    }
    val healthJournalsMap =
        remember { StatsManager.getHealthJournal(healthJournals = healthJournals) }

    FlowRow(
        modifier = modifier.paddingHorizontalMedium(),
        maxLines = 6,
        maxItemsInEachRow = 7,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        weekChars.forEach {
            Text(
                text = stringResource(it),
                style = TextStyles.TextMdSemiBold(),
                color = Colors.Brown100Alpha64,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f).aspectRatio(1f / 1f).padding(vertical = 5.dp)
            )
        }
        for (i in 0 until dayOfWeek + 1) {
            Box(
                modifier = Modifier
                    .background(color = Colors.Transparent, shape = Dimens.Shape.Circle)
                    .weight(1f).aspectRatio(1f / 1f),
            )
        }
        healthJournalsMap.forEachMapComposable { _, healthJournal ->
            val dayFreudScore = remember {
                StatsManager.getHealthJournalFreudScore(
                    healthJournal ?: emptyList()
                )
            }
            val background = dayFreudScore?.palette?.backgroundColor ?: Colors.Brown20
            Box(
                modifier = Modifier.weight(1f).aspectRatio(1f / 1f)
                    .background(color = background, shape = Dimens.Shape.Circle),
            )
        }
    }
}