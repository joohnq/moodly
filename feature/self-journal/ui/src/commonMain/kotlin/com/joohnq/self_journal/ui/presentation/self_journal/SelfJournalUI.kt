package com.joohnq.self_journal.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.mapper.handle
import com.joohnq.self_journal.ui.components.SelfJournalContent
import com.joohnq.self_journal.ui.components.SelfJournalPanel
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalContract
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SelfJournalUI(
    state: SelfJournalContract.State,
    onEvent: (SelfJournalContract.Event) -> Unit = {},
) {
    state.records.handle(
        onLoading = { LoadingUI() },
        onSuccess = { records: List<SelfJournalRecordResource> ->
            DecoratedConvexPanelList(
                panelBackgroundColor = Colors.Brown60,
                containerColor = Colors.White,
                isDark = false,
                image = Drawables.Images.SelfJournalBackground,
                color = Colors.Brown70,
                onAddButton = { onEvent(SelfJournalContract.Event.NavigateToAddSelfJournal) },
                onGoBack = { onEvent(SelfJournalContract.Event.GoBack) },
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
                        onEvent = onEvent
                    )
                },
            )
        }
    )
}

@Preview
@Composable
fun SelfJournalUIPreviewEmpty() {
    SelfJournalUI(
        state = SelfJournalContract.State(
            records = UiState.Success(
                listOf(
                )
            )
        ),
    )
}

@Preview
@Composable
fun SelfJournalUIPreview() {
    SelfJournalUI(
        state = SelfJournalContract.State(
            records = UiState.Success(
                listOf(
                    SelfJournalRecordResource()
                )
            )
        ),
    )
}