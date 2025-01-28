package com.joohnq.sleep_quality.ui.components


import SleepQualityHistoryCard
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.components.SwipeTorRevealCard
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.presentation.sleep_history.event.SleepHistoryEvent

@Composable
fun SleepHistoryLazyItems(records: List<SleepQualityRecord>, onEvent: (SleepHistoryEvent) -> Unit) {
    LazyColumn {
        if (records.isEmpty())
            item {
                SleepQualityNotFound(onClick = { onEvent(SleepHistoryEvent.OnCreateSleepQuality) })
            }
        else
            items(records) { sleepQuality ->
                SwipeTorRevealCard(
                    onAction = {}
                ) { modifier ->
                    SleepQualityHistoryCard(
                        modifier = modifier,
                        record = sleepQuality,
                        onClick = {
                            onEvent(
                                SleepHistoryEvent.OnNavigateToSleepQuality(
                                    sleepQuality.id
                                )
                            )
                        },
                    )
                }
            }
    }
}