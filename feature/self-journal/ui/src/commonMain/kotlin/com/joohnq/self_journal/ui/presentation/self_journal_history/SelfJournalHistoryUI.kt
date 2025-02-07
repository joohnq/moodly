package com.joohnq.self_journal.ui.presentation.self_journal_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.domain.entity.User
import com.joohnq.self_journal.ui.components.SelfJournalsItems
import com.joohnq.self_journal.ui.mapper.getItemsByDate
import com.joohnq.self_journal.ui.mapper.organizeFromCreationSelfJournalFreudScore
import com.joohnq.self_journal.ui.presentation.self_journal_history.event.SelfJournalHistoryEvent
import com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryIntent
import com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryState
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.*
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun AllJournalUI(
    state: SelfJournalHistoryState,
    user: UiState<User>,
    records: UiState<List<SelfJournalRecordResource>>,
    onAction: (SelfJournalHistoryIntent) -> Unit,
    onEvent: (SelfJournalHistoryEvent) -> Unit,
) {
    if (state.openDeleteDialog)
        ImageAlertDialog(
            onDismissRequest = {
                onAction(
                    SelfJournalHistoryIntent.UpdateOpenDeleteDialog(false)
                )
            },
            onConfirmation = {
                onAction(
                    SelfJournalHistoryIntent.UpdateOpenDeleteDialog(false)
                )
                onEvent(SelfJournalHistoryEvent.OnDelete)
            },
            dialogTitle = Res.string.delete_journal,
            dialogText = Res.string.do_you_wish_to_remove_this_journal,
            image = Drawables.Images.DeleteSelfJournalIllustration,
            backgroundColor = Colors.White
        )

    listOf(
        user,
        records,
    ).foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { u: User, records: List<SelfJournalRecordResource> ->
            val recordsMap = records.organizeFromCreationSelfJournalFreudScore(u.dateCreated)
            val dates = recordsMap.keys.toList()
            val items = recordsMap.getItemsByDate(state.selectedDateTime)

            Scaffold(
                containerColor = Colors.Brown10,
                modifier = Modifier.fillMaxSize(),
            ) { padding ->
                Column(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.background(
                            color = Colors.Brown80,
                            shape = Dimens.Shape.BottomMedium
                        ).padding(top = padding.calculateTopPadding(), bottom = 30.dp)
                    ) {
                        TopBar(
                            modifier = Modifier.paddingHorizontalMedium(),
                            isDark = false,
                            onGoBack = { onEvent(SelfJournalHistoryEvent.OnGoBack) },
                        )
                        VerticalSpacer(10.dp)
                        Text(
                            text = stringResource(Res.string.my_journals),
                            style = TextStyles.HeadingSmExtraBold(),
                            color = Colors.White,
                            modifier = Modifier.paddingHorizontalMedium()
                        )
                        VerticalSpacer(10.dp)
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(horizontal = 20.dp),
                            state = rememberLazyListState(initialFirstVisibleItemIndex = dates.lastIndex)
                        ) {
                            items(dates) { date ->
                                val isSelected =
                                    date == state.selectedDateTime
                                DateCard(
                                    isSelected = isSelected,
                                    date = date,
                                    onClick = {
                                        onAction(
                                            SelfJournalHistoryIntent.UpdateSelectedDateTime(
                                                date
                                            )
                                        )
                                    },
                                )
                            }
                        }
                    }
                    VerticalSpacer(20.dp)
                    Text(
                        text = stringResource(Res.string.timeline),
                        style = TextStyles.TextLgExtraBold(),
                        color = Colors.Brown80,
                        modifier = Modifier.fillMaxWidth().paddingHorizontalMedium()
                    )
                    VerticalSpacer(20.dp)
                    SelfJournalsItems(
                        items = items,
                        onClick = { id ->
                            onEvent(
                                SelfJournalHistoryEvent.OnSelectJournalHistory(id)
                            )
                        }, onDelete = { id ->
                            onAction(
                                SelfJournalHistoryIntent.UpdateCurrentDeleteId(id)
                            )
                            onAction(
                                SelfJournalHistoryIntent.UpdateOpenDeleteDialog(true)
                            )
                        }
                    )
                }
            }
        }
    )
}
