package com.joohnq.self_journal.ui.presentation.self_journal_history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.mapper.handle
import com.joohnq.domain.mapper.toFormattedDateString
import com.joohnq.self_journal.ui.components.SelfJournalsHistoryCards
import com.joohnq.self_journal.ui.resource.mapper.toGroupedByDate
import com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryContract
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.ImageAlertDialog
import com.joohnq.shared_resources.components.IsEmpty
import com.joohnq.shared_resources.components.SwipeTorRevealCard
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.delete_journal
import com.joohnq.shared_resources.do_you_wish_to_remove_this_journal
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.ui.mapper.items
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SelfJournalHistoryUI(
    state: SelfJournalHistoryContract.State,
    records: UiState<List<SelfJournalRecordResource>>,
    onIntent: (SelfJournalHistoryContract.Intent) -> Unit,
    onEvent: (SelfJournalHistoryContract.Event) -> Unit,
) {
    if (state.openDeleteDialog)
        ImageAlertDialog(
            onDismissRequest = {
                onIntent(
                    SelfJournalHistoryContract.Intent.UpdateOpenDeleteDialog(false)
                )
            },
            onConfirmation = {
                onIntent(
                    SelfJournalHistoryContract.Intent.UpdateOpenDeleteDialog(false)
                )
                onEvent(SelfJournalHistoryContract.Event.Delete)
            },
            dialogTitle = Res.string.delete_journal,
            dialogText = Res.string.do_you_wish_to_remove_this_journal,
            image = Drawables.Images.SelfJournalDeleting,
            backgroundColor = Colors.White
        )

    records.handle(
        onSuccess = { records ->
            val recordsMap = records.toGroupedByDate()

            Scaffold(
                containerColor = Colors.Brown10
            ) { padding ->
                Column(modifier = Modifier.padding(padding).paddingHorizontalMedium()) {
                    TopBar(
                        modifier = Modifier.fillMaxWidth(),
                        isDark = true,
                        onGoBack = { onEvent(SelfJournalHistoryContract.Event.GoBack) }
                    )
                    VerticalSpacer(20.dp)
                    Text(
                        text = stringResource(Res.string.all_history),
                        style = TextStyles.textLgBold(),
                        color = Colors.Gray80
                    )
                    VerticalSpacer(20.dp)
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(
                            items = recordsMap,
                            empty = {
                                IsEmpty()
                            },
                            title = { date ->
                                Text(
                                    text = date.toFormattedDateString(),
                                    style = TextStyles.textMdBold(),
                                    color = Colors.Gray80
                                )
                            },
                        ) { record ->
                            SwipeTorRevealCard(
                                modifier = Modifier.fillMaxWidth(),
                                onAction = {}
                            ) { modifier ->
                                SelfJournalsHistoryCards(
                                    modifier = modifier,
                                    containerColor = Colors.White,
                                    records = recordsMap,
                                    onClick = {},
                                    onDelete = {}
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun SelfJournalHistoryUIPreview() {
    SelfJournalHistoryUI(
        state = SelfJournalHistoryContract.State(),
        records = UiState.Success(
            listOf(
                SelfJournalRecordResource(),
                SelfJournalRecordResource()
            )
        ),
        onIntent = {},
        onEvent = {}
    )
}
