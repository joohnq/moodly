package com.joohnq.health_journal.ui.presentation.health_journal

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.core.ui.mapper.toWordCount
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.components.HealthJournalContent
import com.joohnq.health_journal.ui.components.HealthJournalPanel
import com.joohnq.health_journal.ui.presentation.health_journal.event.HealthJournalEvent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalState
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HealthJournalUI(
    state: HealthJournalState,
    onEvent: (HealthJournalEvent) -> Unit = {},
) {
    state.healthJournalRecords.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { records: List<HealthJournalRecord> ->
            DecoratedConvexPanelList(
                panelBackgroundColor = Colors.Brown60,
                containerColor = Colors.White,
                isDark = false,
                image = Drawables.Images.HealthJournalBackground,
                color = Colors.Brown70,
                onAddButton = { onEvent(HealthJournalEvent.OnNavigateToAddHealthJournal) },
                onGoBack = { onEvent(HealthJournalEvent.OnGoBack) },
                panel = { modifier ->
                    HealthJournalPanel(
                        modifier = modifier,
                        count = records.size,
                        records = records
                    )
                },
                content = { modifier ->
                    HealthJournalContent(
                        modifier = modifier,
                        records = records,
                        onClick = {
                        },
                        onCreate = {
                            onEvent(HealthJournalEvent.OnNavigateToAddHealthJournal)
                        },
                        onSeeAll = {
                        }
                    )
                },
            )
        }
    )
}

@Preview
@Composable
fun HealthJournalUIPreview() {
    HealthJournalUI(
        state = HealthJournalState(
            healthJournalRecords = UiState.Success(
                listOf(
                    HealthJournalRecord()
                )
            )
        ),
    )
}