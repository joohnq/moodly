package com.joohnq.self_journal.ui.presentation.journaling

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.self_journal.ui.components.SelfJournalCard
import com.joohnq.self_journal.ui.components.SelfJournalStatsCard
import com.joohnq.self_journal.ui.mapper.getSelfJournalsInYear
import com.joohnq.self_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.IsEmpty
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.Title
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JournalingUI(
    padding: PaddingValues,
    records: UiState<List<SelfJournalRecordResource>>,
    onEvent: (JournalingEvent) -> Unit = {},
) {
    records.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { records ->
            val dayPerYear = records.getSelfJournalsInYear()

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
                if (records.isEmpty()) {
                    IsEmpty()
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        modifier = Modifier.heightIn(min = 250.dp)
                    ) {
                        items(records) { journal ->
                            SelfJournalCard(journal) {
                                onEvent(
                                    JournalingEvent.OnNavigateToEditJournaling(journal.id)
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
                    SelfJournalStatsCard(
                        modifier = Modifier.weight(1f),
                        icon = Drawables.Icons.Outlined.Document,
                        title = dayPerYear,
                        color = Colors.Green50,
                        backgroundColor = Colors.Green10,
                        description = stringResource(Res.string.completed)
                    )
                    SelfJournalStatsCard(
                        modifier = Modifier.weight(1f),
                        icon = Drawables.Icons.Chart,
                        title = stringResource(if (records.isEmpty()) Res.string.no_data else records.first().mood.text),
                        color = Colors.Brown60,
                        backgroundColor = Colors.Brown10,
                        description = stringResource(Res.string.emotion)
                    )
                }
                VerticalSpacer(10.dp)
            }
        }
    )
}