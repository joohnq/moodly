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
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.domain.entity.User
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.OrganizeFromCreationHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.ui.components.AllJournalsCard
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalIntent
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.DateCard
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.MainAlertDialog
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.delete_journal
import com.joohnq.shared_resources.do_you_wish_to_remove_this_journal
import com.joohnq.shared_resources.my_journals
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.timeline
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun AllJournalUI(
    state: AllJournalState,
    user: UiState<User>,
    healthJournalRecords: UiState<List<HealthJournalRecord>>,
    onAction: (AllJournalIntent) -> Unit,
    onEvent: (AllJournalEvent) -> Unit,
) {
    listOf(
        user,
        healthJournalRecords,
    ).foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { u: User, healthJournals: List<HealthJournalRecord> ->
            val organizeFromCreationHealthJournalFreudScoreUseCase: OrganizeFromCreationHealthJournalFreudScoreUseCase =
                koinInject()
            val healthJournalMap = organizeFromCreationHealthJournalFreudScoreUseCase(
                creationAt = u.dateCreated,
                healthJournals = healthJournals
            )
            val keys = healthJournalMap.keys.toList()
            val key =
                healthJournalMap.keys.find { it == state.selectedDateTime }
                    ?: healthJournalMap.keys.last()
            val list = healthJournalMap[key]

            if (state.openDeleteDialog)
                MainAlertDialog(
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
                            state = rememberLazyListState(initialFirstVisibleItemIndex = keys.lastIndex)
                        ) {
                            items(keys) { date ->
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
                                    onEvent = onEvent,
                                    onDelete = {
                                        onAction(
                                            AllJournalIntent.UpdateCurrentDeleteId(
                                                healthJournal.id
                                            )
                                        )
                                        onAction(
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
