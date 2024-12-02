package com.joohnq.moodapp.ui.presentation.all_journals

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
import com.joohnq.moodapp.ui.components.MainAlertDialog
import com.joohnq.moodapp.ui.components.TopBar
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.moodapp.ui.presentation.all_journals.state.AllJournalState
import com.joohnq.moodapp.ui.presentation.loading.LoadingUI
import com.joohnq.moodapp.ui.state.UiState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.util.helper.StatsManager
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.delete_journal
import moodapp.composeapp.generated.resources.do_you_wish_to_remove_this_journal
import moodapp.composeapp.generated.resources.my_journals
import moodapp.composeapp.generated.resources.timeline
import org.jetbrains.compose.resources.stringResource

@Composable
fun AllJournalUI(state: AllJournalState) {
    UiState.onFold(
        state.user,
        state.healthJournalRecords,
        onLoading = { LoadingUI() },
        onAllSuccess = {
            val user = state.user.getValue()
            val healthJournalRecords = state.healthJournalRecords.getValue()
            val healthJournalMap = StatsManager.getHealthJournalBasedOnUserEntry(
                user.dateCreated,
                healthJournalRecords
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
                            AllJournalIntent.UpdateOpenDeleteDialog(false)
                        )
                    },
                    onConfirmation = {
                        state.onAllAction(
                            AllJournalIntent.UpdateOpenDeleteDialog(false)
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
                                AllJournalDateCard(
                                    isSelected = isSelected,
                                    date = date,
                                    onAllAction = state.onAllAction,
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
                                            AllJournalIntent.UpdateCurrentDeleteId(healthJournal.id)
                                        )
                                        state.onAllAction(
                                            AllJournalIntent.UpdateOpenDeleteDialog(true)
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
