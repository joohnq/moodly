package com.joohnq.stress_level.history.presentation

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
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.view.EmptyView
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.impl.ui.component.StressLevelHistoryCard
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.mapper.MapMapper.items
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelHistoryContent(
    state: StressLevelHistoryContract.State,
    onIntent: (StressLevelHistoryContract.Intent) -> Unit = {},
    onEvent: (StressLevelHistoryContract.Event) -> Unit = {},
) {
    when {
        state.isLoading -> Unit
        state.isError != null -> Unit
        else ->
            SuccessView(
                items = state.items,
                onEvent = onEvent,
                onIntent = onIntent
            )
    }
}

@Composable
private fun SuccessView(
    items: Map<LocalDate, List<StressLevelRecordResource>>,
    onEvent: (StressLevelHistoryContract.Event) -> Unit,
    onIntent: (StressLevelHistoryContract.Intent) -> Unit,
) {
    Scaffold(
        containerColor = Colors.Brown10
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).paddingHorizontalMedium()
        ) {
            AppTopBar(
                modifier = Modifier.fillMaxWidth(),
                isDark = true,
                onGoBack = { onEvent(StressLevelHistoryContract.Event.GoBack) }
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
                    items = items,
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
                    StressLevelHistoryCard(
                        item = record,
                        onDelete = {
                            onIntent(StressLevelHistoryContract.Intent.Delete(record.id))
                        }
                    )
                }
            }
        }
    }
}