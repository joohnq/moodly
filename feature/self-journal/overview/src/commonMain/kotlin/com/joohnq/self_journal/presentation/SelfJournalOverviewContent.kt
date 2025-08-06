package com.joohnq.self_journal.presentation

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.presentation.components.SelfJournalOverviewContentBody
import com.joohnq.self_journal.presentation.components.SelfJournalOverviewPanel
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.view.LoadingView
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun SelfJournalOverviewContent(
    state: SelfJournalOverviewContract.State,
    onIntent: (SelfJournalOverviewContract.Intent) -> Unit = {},
    onEvent: (SelfJournalOverviewContract.Event) -> Unit = {},
) {
    when {
        state.isLoading -> LoadingView()
        state.isError != null -> Unit
        else ->
            SuccessView(
                state = state,
                onEvent = onEvent,
                onIntent = onIntent
            )
    }
}

@Composable
private fun SuccessView(
    state: SelfJournalOverviewContract.State,
    onEvent: (SelfJournalOverviewContract.Event) -> Unit,
    onIntent: (SelfJournalOverviewContract.Intent) -> Unit,
) {
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
                items = state.items
            )
        },
        body = { modifier ->
            SelfJournalOverviewContentBody(
                modifier = modifier,
                items = state.items,
                onIntent = onIntent,
                onEvent = onEvent
            )
        }
    )
}