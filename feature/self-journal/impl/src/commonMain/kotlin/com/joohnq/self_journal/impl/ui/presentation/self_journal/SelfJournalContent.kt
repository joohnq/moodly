package com.joohnq.self_journal.impl.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.components.SelfJournalContent
import com.joohnq.self_journal.impl.ui.components.SelfJournalPanel
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.view.LoadingView
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.mapper.foldComposable

@Composable
fun SelfJournalContent(
    state: SelfJournalContract.State,
    onEvent: (SelfJournalContract.Event) -> Unit = {},
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
                onAddButton = { onEvent(SelfJournalContract.Event.OnNavigateToAddSelfJournal) },
                onGoBack = { onEvent(SelfJournalContract.Event.OnGoBack) },
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