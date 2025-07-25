package com.joohnq.stress_level.impl.ui.presentation.stress_history

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
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.view.EmptyView
import com.joohnq.shared_resources.components.layout.SwipeableCardLayout
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.impl.ui.component.StressLevelHistoryCard
import com.joohnq.stress_level.impl.ui.mapper.toGroupedByDate
import com.joohnq.stress_level.impl.ui.presentation.stress_history.event.StressHistoryEvent
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelState
import com.joohnq.ui.mapper.foldComposable
import com.joohnq.ui.mapper.items
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressHistoryContent(
    state: StressLevelState,
    onAction: (StressLevelIntent) -> Unit = {},
    onEvent: (StressHistoryEvent) -> Unit = {},
) {
    state.records.foldComposable(
        onSuccess = { records ->
            val recordsMap = records.toGroupedByDate()
            Scaffold(
                containerColor = Colors.Brown10
            ) { padding ->
                Column(
                    modifier = Modifier.padding(padding).paddingHorizontalMedium()
                ) {
                    AppTopBar(
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
                                EmptyView()
                            },
                            title = { date ->
                                Text(
                                    text = date.toFormattedDateString(),
                                    style = TextStyles.TextMdBold(),
                                    color = Colors.Gray80
                                )
                            },
                        ) { record ->
                            SwipeableCardLayout(
                                modifier = Modifier.fillMaxWidth(),
                                onAction = {
                                    onAction(StressLevelIntent.Delete(record.id))
                                }
                            ) { modifier ->
                                StressLevelHistoryCard(
                                    modifier = modifier,
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