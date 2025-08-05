package com.joohnq.self_journal.presentation

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.presentation.components.SelfJournalOverviewContentBody
import com.joohnq.self_journal.presentation.components.SelfJournalOverviewPanel
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.view.LoadingView
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.mapper.UiStateMapper.foldComposable

@Composable
fun SelfJournalOverviewContent(
    state: SelfJournalOverviewContract.State,
    onIntent: (SelfJournalOverviewContract.Intent) -> Unit = {},
    onEvent: (SelfJournalOverviewContract.Event) -> Unit = {},
) {
    state.records.foldComposable(
        onLoading = { LoadingView() },
        onSuccess = { records: List<SelfJournalRecordResource> ->
            ConvexGroupLazyLayout(
                panelBackgroundColor = Colors.Brown60,
                containerColor = Colors.White,
                isDark = false,
                image = Drawables.Images.SelfJournalBackground,
                color = Colors.Brown70,
                onAddButton = { onEvent(SelfJournalOverviewContract.Event.NavigateToAddSelfJournal) },
                onGoBack = { onEvent(SelfJournalOverviewContract.Event.GoBack) },
                panel = { modifier ->
                    SelfJournalOverviewPanel(
                        modifier = modifier,
                        count = records.size,
                        records = records
                    )
                },
                body = { modifier ->
                    SelfJournalOverviewContentBody(
                        modifier = modifier,
                        records = records,
                        onIntent = onIntent,
                        onEvent = onEvent
                    )
                }
            )
        }
    )
}