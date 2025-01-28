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
import com.joohnq.core.ui.mapper.forEachMapComposable
import com.joohnq.core.ui.mapper.getCurrentWeekDayIndex
import com.joohnq.core.ui.mapper.items
import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.freud_score.ui.mapper.toResource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.CalculateHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.domain.use_case.OrganizeByDateHealthJournalUseCase
import com.joohnq.shared_resources.remember.rememberGetNow
import com.joohnq.shared_resources.remember.rememberWeekChars
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun HealthJournalComponent(
    modifier: Modifier = Modifier,
    healthJournals: List<HealthJournalRecord>,
) {
    val now = rememberGetNow()
    val dayOfWeek = now.getCurrentWeekDayIndex()
    val organizeByDateHealthJournalUseCase: OrganizeByDateHealthJournalUseCase = koinInject()
    val healthJournalsMap: Map<LocalDate, List<HealthJournalRecord>?> =
        remember { organizeByDateHealthJournalUseCase(date = now, healthJournals = healthJournals) }
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
    val now = rememberGetNow()
    val dayOfWeek = now.getCurrentWeekDayIndex()
    val weekChars = rememberWeekChars()
    val organizeByDateHealthJournalUseCase: OrganizeByDateHealthJournalUseCase = koinInject()
    val healthJournalsMap: Map<LocalDate, List<HealthJournalRecord>?> =
        remember { organizeByDateHealthJournalUseCase(date = now, healthJournals = healthJournals) }

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
                    .then(if (healthJournal?.firstOrNull()?.createdAt != null)
                        Modifier.clickable {
                            onClick(healthJournal.firstOrNull()?.createdAt?.date!!)
                        }
                    else Modifier)
            )
        }
    }
}