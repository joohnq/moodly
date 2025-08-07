package com.joohnq.self_journal.presentation

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
import com.joohnq.api.mapper.LocalDateMapper.toFormattedDateString
import com.joohnq.self_journal.presentation.components.SelfJournalHistoryCard
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.view.EmptyView
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.ui.mapper.MapMapper.items
import com.joohnq.ui.mapper.MapMapper.itemsIndexed
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalHistoryContent(
    state: SelfJournalHistoryContract.State,
    onIntent: (SelfJournalHistoryContract.Intent) -> Unit = {},
    onEvent: (SelfJournalHistoryContract.Event) -> Unit = {},
) {
    when {
        state.isLoading -> Unit
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
    state: SelfJournalHistoryContract.State,
    onEvent: (SelfJournalHistoryContract.Event) -> Unit,
    onIntent: (SelfJournalHistoryContract.Intent) -> Unit,
) {
    Scaffold(
        containerColor = Colors.Brown10
    ) { padding ->
        Column(modifier = Modifier.padding(padding).paddingHorizontalMedium()) {
            AppTopBar(
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
                    items = state.items,
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
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        itemsIndexed(
                            items = state.items,
                            empty = {
                                EmptyView()
                            }
                        ) { i, lastIndex, item ->
                            SelfJournalHistoryCard(
                                isNotFirst = i != 0,
                                isNotLast = i != lastIndex,
                                item = item,
                                onClick = {},
                                onDelete = { id ->
                                    onIntent(
                                        SelfJournalHistoryContract.Intent.Delete(id)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
