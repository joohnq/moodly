package com.joohnq.health_journal.ui.presentation.journaling

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.health_journal.ui.components.HealthJournalCard
import com.joohnq.health_journal.ui.components.HealthJournalStatsCard
import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_journals
import com.joohnq.shared_resources.completed
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.Title
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.document_your_mental_journal
import com.joohnq.shared_resources.emotion
import com.joohnq.shared_resources.empty
import com.joohnq.shared_resources.journal_stats
import com.joohnq.shared_resources.more_journal_stats
import com.joohnq.shared_resources.no_data
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.your_entries
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JournalingUI(
    journals: UiState<List<HealthJournalRecord>>,
    onEvent: (JournalingEvent) -> Unit = {},
) {
    journals.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { healthJournals ->
            val getHealthJournalsInYearUseCase = koinInject<GetHealthJournalsInYearUseCase>()
            val dayPerYear =
                remember { getHealthJournalsInYearUseCase(healthJournals = healthJournals) }
            Scaffold(
                containerColor = Colors.Brown10,
                modifier = Modifier.fillMaxSize()
            ) {
                val padding = PaddingValues(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding() + 80.dp,
                    start = it.calculateStartPadding(LayoutDirection.Ltr),
                    end = it.calculateEndPadding(LayoutDirection.Rtl)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(padding)
                ) {
                    VerticalSpacer(20.dp)
                    Column(modifier = Modifier.paddingHorizontalMedium()) {
                        Text(
                            text = stringResource(Res.string.your_entries),
                            style = TextStyles.HeadingMdExtraBold(),
                            color = Colors.Brown80
                        )
                        Text(
                            text = stringResource(Res.string.document_your_mental_journal),
                            style = TextStyles.ParagraphLg(),
                            color = Colors.Brown100Alpha64
                        )
                    }
                    Title(
                        modifier = Modifier.padding(vertical = 32.dp).paddingHorizontalMedium(),
                        text = Res.string.all_journals
                    )
                    if (healthJournals.isEmpty()) {
                        Box(
                            modifier = Modifier.height(250.dp).fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(Res.string.empty),
                                style = TextStyles.Text2xlExtraBold(),
                                color = Colors.Brown100Alpha64,
                            )
                        }
                    } else {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(horizontal = 20.dp),
                            modifier = Modifier.heightIn(min = 250.dp)
                        ) {
                            items(healthJournals) { journal ->
                                HealthJournalCard(journal) {
                                    onEvent(
                                        JournalingEvent.OnNavigateToEditJournalingScreen(journal.id)
                                    )
                                }
                            }
                        }
                    }
                    VerticalSpacer(20.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(Res.string.journal_stats),
                            style = TextStyles.TextLgExtraBold(),
                            color = Colors.Brown80,
                        )
                        FilledIconButton(
                            modifier = Modifier.size(48.dp),
                            colors = ComponentColors.IconButton.TransparentButton(Colors.Brown100Alpha64),
                            onClick = { onEvent(JournalingEvent.OnNavigateToAllJournals) }
                        ) {
                            Icon(
                                painter = painterResource(Drawables.Icons.MoreHorizontal),
                                contentDescription = stringResource(Res.string.more_journal_stats),
                                tint = Colors.Brown100Alpha64,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    VerticalSpacer(10.dp)
                    FlowRow(
                        maxItemsInEachRow = 2,
                        maxLines = 1,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.paddingHorizontalMedium()
                    ) {
                        HealthJournalStatsCard(
                            modifier = Modifier.weight(1f),
                            icon = Drawables.Icons.Document,
                            title = dayPerYear,
                            color = Colors.Green50,
                            backgroundColor = Colors.Green10,
                            description = stringResource(Res.string.completed)
                        )
                        HealthJournalStatsCard(
                            modifier = Modifier.weight(1f),
                            icon = Drawables.Icons.Chart,
                            title = stringResource(if (healthJournals.isEmpty()) Res.string.no_data else healthJournals.first().mood.toResource().text),
                            color = Colors.Brown60,
                            backgroundColor = Colors.Brown10,
                            description = stringResource(Res.string.emotion)
                        )
                    }
                }
            }
        }
    )
}