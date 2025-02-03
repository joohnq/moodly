package com.joohnq.health_journal.ui.presentation.all_journals

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
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.mapper.getItemsByDate
import com.joohnq.health_journal.domain.use_case.OrganizeFromCreationHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.ui.components.SelfJournalsItems
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalIntent
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalState
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
    state: AllJournalState,
    user: UiState<User>,
    organizeFromCreationHealthJournalFreudScoreUseCase: OrganizeFromCreationHealthJournalFreudScoreUseCase,
    records: UiState<List<HealthJournalRecord>>,
    onAction: (AllJournalIntent) -> Unit,
    onEvent: (AllJournalEvent) -> Unit,
) {
    if (state.openDeleteDialog)
        ImageAlertDialog(
            onDismissRequest = {
                onAction(
                    AllJournalIntent.UpdateOpenDeleteDialog(false)
                )
            },
            onConfirmation = {
                onAction(
                    AllJournalIntent.UpdateOpenDeleteDialog(false)
                )
                onEvent(AllJournalEvent.OnDelete)
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
        onSuccess = { u: User, records: List<HealthJournalRecord> ->
            val recordsMap = organizeFromCreationHealthJournalFreudScoreUseCase(
                creationAt = u.dateCreated,
                healthJournals = records
            )
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
                            onGoBack = { onEvent(AllJournalEvent.OnGoBack) },
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
                                            AllJournalIntent.UpdateSelectedDateTime(
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
                                AllJournalEvent.OnSelectJournal(id)
                            )
                        }, onDelete = { id ->
                            onAction(
                                AllJournalIntent.UpdateCurrentDeleteId(id)
                            )
                            onAction(
                                AllJournalIntent.UpdateOpenDeleteDialog(true)
                            )
                        }
                    )
                }
            }
        }
    )
}
