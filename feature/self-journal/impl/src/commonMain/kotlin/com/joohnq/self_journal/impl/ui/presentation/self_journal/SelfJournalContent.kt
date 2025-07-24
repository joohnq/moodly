package com.joohnq.self_journal.impl.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import com.joohnq.ui.mapper.foldComposable
import com.joohnq.self_journal.impl.ui.components.SelfJournalContent
import com.joohnq.self_journal.impl.ui.components.SelfJournalPanel
import com.joohnq.self_journal.impl.ui.presentation.self_journal.event.SelfJournalEvent
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.impl.ui.viewmodel.SelfJournalState
import com.joohnq.shared_resources.components.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.view.LoadingView
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun SelfJournalContent(
    state: SelfJournalState,
    onEvent: (SelfJournalEvent) -> Unit = {},
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
                onAddButton = { onEvent(SelfJournalEvent.OnNavigateToAddSelfJournal) },
                onGoBack = { onEvent(SelfJournalEvent.OnGoBack) },
                panel = { modifier ->
                    SelfJournalPanel(
                        modifier = modifier,
                        count = records.size,
                        records = records
                    )
                },
                body = { modifier ->
                    SelfJournalContent(
                        modifier = modifier,
                        records = records,
                        onEvent = onEvent
                    )
                },
            )
        }
    )
}