package com.joohnq.health_journal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.freud_score.ui.mapper.toResource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.CalculateHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.domain.use_case.OrganizeByDateHealthJournalUseCase
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.friday_char
import com.joohnq.shared_resources.monday_char
import com.joohnq.shared_resources.saturday_char
import com.joohnq.shared_resources.sunday_char
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.thursday_char
import com.joohnq.shared_resources.tuesday_char
import com.joohnq.shared_resources.util.mappers.forEachMapComposable
import com.joohnq.shared_resources.util.mappers.items
import com.joohnq.shared_resources.wednesday_char
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun HealthJournalComponent(
    modifier: Modifier = Modifier,
    healthJournals: List<HealthJournalRecord>,
) {
    val dayOfWeek =
        remember { DatetimeProvider.getCurrentWeekDay(DatetimeProvider.getCurrentDateTime()) }
    val organizeByDateHealthJournalUseCase: OrganizeByDateHealthJournalUseCase = koinInject()
    val healthJournalsMap: Map<LocalDate, List<HealthJournalRecord>?> =
        remember { organizeByDateHealthJournalUseCase(healthJournals = healthJournals) }
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
    healthJournals: List<HealthJournalRecord>,
    onClick: (LocalDate) -> Unit,
) {
    val dayOfWeek = DatetimeProvider.getCurrentWeekDay(DatetimeProvider.getCurrentDateTime())
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
    val organizeByDateHealthJournalUseCase: OrganizeByDateHealthJournalUseCase = koinInject()
    val healthJournalsMap: Map<LocalDate, List<HealthJournalRecord>?> =
        remember { organizeByDateHealthJournalUseCase(healthJournals = healthJournals) }

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
        healthJournalsMap.forEachMapComposable { _, healthJournal: List<HealthJournalRecord>? ->
            val calculateHealthJournalFreudScoreUseCase: CalculateHealthJournalFreudScoreUseCase =
                koinInject()
            val dayFreudScore: FreudScore? =
                remember { calculateHealthJournalFreudScoreUseCase(healthJournal ?: emptyList()) }
            val resource = dayFreudScore?.toResource()
            val background = resource?.palette?.backgroundColor ?: Colors.Brown20
            Box(
                modifier = Modifier.weight(1f).aspectRatio(1f / 1f)
                    .background(color = background, shape = Dimens.Shape.Circle)
                    .then(if (healthJournal?.firstOrNull()?.date?.date != null)
                        Modifier.clickable {
                            onClick(healthJournal.firstOrNull()?.date?.date!!)
                        }
                    else Modifier)
            )
        }
    }
}