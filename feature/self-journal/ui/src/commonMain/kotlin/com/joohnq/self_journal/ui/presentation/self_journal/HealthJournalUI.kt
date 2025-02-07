package com.joohnq.self_journal.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.ui.SelfJournalRecordResource
import com.joohnq.self_journal.ui.components.SelfJournalContent
import com.joohnq.self_journal.ui.components.SelfJournalPanel
import com.joohnq.self_journal.ui.presentation.self_journal.event.SelfJournalEvent
import com.joohnq.self_journal.ui.viewmodel.SelfJournalState
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SelfJournalUI(
    state: SelfJournalState,
    onEvent: (SelfJournalEvent) -> Unit = {},
) {
    state.records.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { records: List<SelfJournalRecordResource> ->
            DecoratedConvexPanelList(
                panelBackgroundColor = Colors.Brown60,
                containerColor = Colors.White,
                isDark = false,
                image = Drawables.Images.SelfJournalBackground,
                color = Colors.Brown70,
                onAddButton = { onEvent(SelfJournalEvent.OnNavigateToAddSelfJournal) },
                onGoBack = { onEvent(SelfJournalEvent.OnGoBack) },
                panel = { modifier ->
                    SelfJournalPanel(
                        modifier = modifier,
                        count = records.size,
                        records = records
                    )
                },
                content = { modifier ->
                    SelfJournalContent(
                        modifier = modifier,
                        records = records,
                        onClick = {
                        },
                        onCreate = {
                            onEvent(SelfJournalEvent.OnNavigateToAddSelfJournal)
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
fun SelfJournalUIPreview() {
    SelfJournalUI(
        state = SelfJournalState(
            records = UiState.Success(
                listOf(
                    SelfJournalRecordResource()
                )
            )
        ),
    )
}