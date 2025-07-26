package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality_history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.layout.SwipeableCardLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.impl.ui.component.SleepQualityHistoryCard
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityContract
import com.joohnq.ui.mapper.foldComposable
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepQualityHistoryContent(
    state: SleepQualityContract.State,
    onEvent: (SleepQualityHistoryContract.Event) -> Unit = {},
    onAction: (SleepQualityContract.Intent) -> Unit = {},
) {
    state.records.foldComposable(
        onSuccess = { records ->
            Scaffold(
                containerColor = Colors.Brown10
            ) { padding ->
                Column(modifier = Modifier.padding(padding).paddingHorizontalMedium()) {
                    AppTopBar(
                        modifier = Modifier.fillMaxWidth(),
                        isDark = true,
                        onGoBack = { onEvent(SleepQualityHistoryContract.Event.OnGoBack) }
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
                        items(records) { record ->
                            SwipeableCardLayout(
                                modifier = Modifier.fillMaxWidth(),
                                onAction = { onAction(SleepQualityContract.Intent.Delete(record.id)) }
                            ) { modifier ->
                                SleepQualityHistoryCard(
                                    modifier = modifier,
                                    record = record
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
