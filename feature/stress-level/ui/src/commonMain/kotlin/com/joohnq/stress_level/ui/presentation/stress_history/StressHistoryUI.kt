package com.joohnq.stress_level.ui.presentation.stress_history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.core.ui.mapper.items
import com.joohnq.core.ui.mapper.toFormattedDateString
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.IsEmpty
import com.joohnq.shared_resources.components.SwipeTorRevealCard
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.ui.component.StressLevelHistoryCard
import com.joohnq.stress_level.ui.mapper.toGroupedByDate
import com.joohnq.stress_level.ui.presentation.stress_history.event.StressHistoryEvent
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.ui.viewmodel.StressLevelState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressHistoryUI(
    state: StressLevelState,
    onEvent: (StressHistoryEvent) -> Unit,
) {
    state.records.foldComposable(
        onSuccess = { records ->
            val recordsMap = records.toGroupedByDate()
            Scaffold(
                containerColor = Colors.Brown10
            ) { padding ->
                Column(modifier = Modifier.paddingHorizontalMedium()) {
                    TopBar(
                        modifier = Modifier.fillMaxWidth(),
                        isDark = true,
                        onGoBack = { onEvent(StressHistoryEvent.OnGoBack) }
                    )
                    VerticalSpacer(20.dp)
                    Text(
                        text = stringResource(Res.string.all_history),
                        style = TextStyles.TextLgBold(),
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
                                    style = TextStyles.TextMdBold(),
                                    color = Colors.Gray80
                                )
                            },
                        ) { record ->
                            SwipeTorRevealCard(
                                modifier = Modifier.fillMaxWidth(),
                                onAction = {}
                            ) { modifier ->
                                StressLevelHistoryCard(
                                    modifier = modifier,
                                    containerColor = Colors.White,
                                    record = record,
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
fun StressHistoryUIPreview() {
    StressHistoryUI(
        state = StressLevelState(
            records = UiState.Success(
                listOf(
                    StressLevelRecordResource(),
                    StressLevelRecordResource(),
                    StressLevelRecordResource(),
                    StressLevelRecordResource(),
                    StressLevelRecordResource(),
                )
            )
        ),
        onEvent = {}
    )
}

@Preview
@Composable
fun StressHistoryUIPreviewEmpty() {
    StressHistoryUI(
        state = StressLevelState(
            records = UiState.Success(
                listOf()
            )
        ),
        onEvent = {}
    )
}