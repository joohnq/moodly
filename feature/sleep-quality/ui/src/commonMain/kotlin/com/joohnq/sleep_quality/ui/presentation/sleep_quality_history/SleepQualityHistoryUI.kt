package com.joohnq.sleep_quality.ui.presentation.sleep_quality_history

import SleepQualityHistoryCard
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
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.mapper.handle
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.SwipeTorRevealCard
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.viewmodel.SleepQualityContract
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepQualityHistoryUI(
    state: SleepQualityContract.State,
    onEvent: (SleepQualityHistoryContract.Event) -> Unit = {},
    onIntent: (SleepQualityContract.Intent) -> Unit = {},
) {
    state.records.handle(
        onSuccess = { records ->
            Scaffold(
                containerColor = Colors.Brown10
            ) { padding ->
                Column(modifier = Modifier.padding(padding).paddingHorizontalMedium()) {
                    TopBar(
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
                            SwipeTorRevealCard(
                                modifier = Modifier.fillMaxWidth(),
                                onAction = { onIntent(SleepQualityContract.Intent.Delete(record.id)) },
                            ) { modifier ->
                                SleepQualityHistoryCard(
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
fun SleepHistoryUIPreview() {
    SleepQualityHistoryUI(
        state = SleepQualityContract.State(
            records = UiState.Success(
                listOf(
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                )
            )
        ),
    )
}

@Preview
@Composable
fun SleepHistoryUIPreviewEmpty() {
    SleepQualityHistoryUI(
        state = SleepQualityContract.State(
            records = UiState.Success(
                listOf()
            )
        ),
    )
}