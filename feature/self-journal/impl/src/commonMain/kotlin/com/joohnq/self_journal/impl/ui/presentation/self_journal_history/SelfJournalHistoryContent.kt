package com.joohnq.self_journal.impl.ui.presentation.self_journal_history

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
import com.joohnq.api.mapper.toFormattedDateString
import com.joohnq.self_journal.impl.ui.components.SelfJournalsHistoryCards
import com.joohnq.self_journal.impl.ui.mapper.toGroupedByDate
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.layout.ImageDialogLayout
import com.joohnq.shared_resources.components.layout.SwipeableCardLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.view.EmptyView
import com.joohnq.shared_resources.delete_journal
import com.joohnq.shared_resources.do_you_wish_to_remove_this_journal
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.foldComposable
import com.joohnq.ui.mapper.items
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalHistoryContent(
    state: SelfJournalHistoryContract.State,
    records: UiState<List<SelfJournalRecordResource>>,
    onAction: (SelfJournalHistoryContract.Intent) -> Unit = {},
    onEvent: (SelfJournalHistoryContract.Event) -> Unit = {},
) {
    if (state.openDeleteDialog) {
        ImageDialogLayout(
            onDismissRequest = {
                onAction(
                    SelfJournalHistoryContract.Intent.UpdateOpenDeleteDialog(false)
                )
            },
            onConfirmation = {
                onAction(
                    SelfJournalHistoryContract.Intent.UpdateOpenDeleteDialog(false)
                )
                onEvent(SelfJournalHistoryContract.Event.OnDelete)
            },
            dialogTitle = Res.string.delete_journal,
            dialogText = Res.string.do_you_wish_to_remove_this_journal,
            image = Drawables.Images.SelfJournalDeleting,
            backgroundColor = Colors.White
        )
    }

    records.foldComposable(
        onSuccess = { records ->
            val recordsMap = records.toGroupedByDate()

            Scaffold(
                containerColor = Colors.Brown10
            ) { padding ->
                Column(modifier = Modifier.padding(padding).paddingHorizontalMedium()) {
                    AppTopBar(
                        modifier = Modifier.fillMaxWidth(),
                        isDark = true,
                        onGoBack = { onEvent(SelfJournalHistoryContract.Event.OnGoBack) }
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
                                EmptyView()
                            },
                            title = { date ->
                                Text(
                                    text = date.toFormattedDateString(),
                                    style = TextStyles.textMdBold(),
                                    color = Colors.Gray80
                                )
                            }
                        ) { record ->
                            SwipeableCardLayout(
                                modifier = Modifier.fillMaxWidth(),
                                onAction = {}
                            ) { modifier ->
                                SelfJournalsHistoryCards(
                                    modifier = modifier,
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
