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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.joohnq.health_journal.ui.components.HealthJournalCard
import com.joohnq.health_journal.ui.components.HealthJournalStatsCard
import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.health_journal.ui.presentation.journaling.state.JournalingState
import com.joohnq.mood.ui.MoodResource.Companion.toResource
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.all_journals
import com.joohnq.shared.ui.completed
import com.joohnq.shared.ui.components.Title
import com.joohnq.shared.ui.components.VerticalSpacer
import com.joohnq.shared.ui.document_your_mental_journal
import com.joohnq.shared.ui.emotion
import com.joohnq.shared.ui.journal_stats
import com.joohnq.shared.ui.more_journal_stats
import com.joohnq.shared.ui.no_data
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.ComponentColors
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared.ui.theme.TextStyles
import com.joohnq.shared.ui.your_entries
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JournalingUI(
    state: JournalingState,
) {
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
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
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
            Title(text = Res.string.all_journals)
            if (state.journals.isEmpty()) {
                Box(
                    modifier = Modifier.height(250.dp).fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Empty",
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
                    items(state.journals) { journal ->
                        HealthJournalCard(
                            journal = journal,
                        ) {
                            state.onEvent(
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
                    onClick = { state.onEvent(JournalingEvent.OnNavigateToAllJournals) }
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
                    title = "",
                    color = Colors.Green50,
                    backgroundColor = Colors.Green10,
                    desc = stringResource(Res.string.completed)
                )
                HealthJournalStatsCard(
                    modifier = Modifier.weight(1f),
                    icon = Drawables.Icons.Chart,
                    title = stringResource(if (state.journals.isEmpty()) Res.string.no_data else state.journals.first().mood.toResource().text),
                    color = Colors.Brown60,
                    backgroundColor = Colors.Brown10,
                    desc = stringResource(Res.string.emotion)
                )
            }
        }
    }
}