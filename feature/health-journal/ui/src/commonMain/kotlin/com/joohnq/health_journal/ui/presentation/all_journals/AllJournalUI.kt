package com.joohnq.health_journal.ui.presentation.all_journals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.presentation.loading.LoadingUI
import com.joohnq.domain.entity.User
import com.joohnq.health_journal.domain.use_case.OrganizeFromCreationHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.ui.components.AllJournalsCard
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.health_journal.ui.presentation.all_journals.state.AllJournalState
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModelIntent
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.components.DateCard
import com.joohnq.shared.ui.components.MainAlertDialog
import com.joohnq.shared.ui.components.TopBar
import com.joohnq.shared.ui.components.VerticalSpacer
import com.joohnq.shared.ui.delete_journal
import com.joohnq.shared.ui.do_you_wish_to_remove_this_journal
import com.joohnq.shared.ui.my_journals
import com.joohnq.shared.ui.state.UiState
import com.joohnq.shared.ui.state.UiState.Companion.getValue
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared.ui.theme.TextStyles
import com.joohnq.shared.ui.timeline
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun AllJournalUI(state: AllJournalState) {
    UiState.onFold(
        state.user,
        state.healthJournalRecords,
        onLoading = { LoadingUI() },
        onAllSuccess = {
            val user: User = state.user.getValue()
            val healthJournalRecords = state.healthJournalRecords.getValue()
            val organizeFromCreationHealthJournalFreudScoreUseCase: OrganizeFromCreationHealthJournalFreudScoreUseCase =
                koinInject()
            val healthJournalMap = organizeFromCreationHealthJournalFreudScoreUseCase(
                creationDate = user.dateCreated,
                healthJournals = healthJournalRecords
            )
            val keys = healthJournalMap.keys.toList()
            val key =
                healthJournalMap.keys.find { it == state.allJournalViewModelState.selectedDateTime }
                    ?: healthJournalMap.keys.last()
            val list = healthJournalMap[key]

            if (state.allJournalViewModelState.openDeleteDialog)
                MainAlertDialog(
                    onDismissRequest = {
                        state.onAllAction(
                            AllJournalViewModelIntent.UpdateOpenDeleteDialog(false)
                        )
                    },
                    onConfirmation = {
                        state.onAllAction(
                            AllJournalViewModelIntent.UpdateOpenDeleteDialog(false)
                        )
                        state.onEvent(AllJournalEvent.OnDelete)
                    },
                    dialogTitle = Res.string.delete_journal,
                    dialogText = Res.string.do_you_wish_to_remove_this_journal,
                    icon = Drawables.Icons.Trash,
                    backgroundColor = Colors.White
                )

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
                            onGoBack = { state.onEvent(AllJournalEvent.OnGoBack) },
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
                            state = rememberLazyListState(initialFirstVisibleItemIndex = keys.lastIndex)
                        ) {
                            items(keys) { date ->
                                val isSelected =
                                    date == state.allJournalViewModelState.selectedDateTime
                                DateCard(
                                    isSelected = isSelected,
                                    date = date,
                                    onClick = {
                                        state.onAllAction(
                                            AllJournalViewModelIntent.UpdateSelectedDateTime(
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
                    if (list == null) {
                        Box(
                            modifier = Modifier.height(250.dp).fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Empty",
                                style = TextStyles.Text2xlExtraBold(),
                                color = Colors.Brown100Alpha64,
                            )
                        }
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            itemsIndexed(list) { i, healthJournal ->
                                AllJournalsCard(
                                    i = i,
                                    healthJournal = healthJournal,
                                    lastIndex = list.lastIndex,
                                    onEvent = state.onEvent,
                                    onDelete = {
                                        state.onAllAction(
                                            AllJournalViewModelIntent.UpdateCurrentDeleteId(
                                                healthJournal.id
                                            )
                                        )
                                        state.onAllAction(
                                            AllJournalViewModelIntent.UpdateOpenDeleteDialog(true)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
