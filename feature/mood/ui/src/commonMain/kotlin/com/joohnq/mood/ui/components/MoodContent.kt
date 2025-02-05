package com.joohnq.mood.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.shared_resources.components.CircularLoading
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun MoodContent(
    modifier: Modifier = Modifier,
    statsRecord: StatsRecord,
    statsRecords: UiState<List<StatsRecord>>,
    onEvent: (MoodEvent) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = statsRecord.description,
            style = TextStyles.TextMdSemiBold(),
            color = Colors.Brown100Alpha64,
            modifier = Modifier.fillMaxWidth().paddingHorizontalMedium()
        )
        VerticalSpacer(40.dp)
        statsRecords.foldComposable(
            onLoading = { CircularLoading(Modifier.fillMaxWidth().height(250.dp)) },
            onSuccess = { statsRecords: List<StatsRecord> ->
                MoodBarStatistic(
                    statsRecords = statsRecords.reversed(),
                    currentStatsRecord = statsRecord,
                    onClick = { onEvent(MoodEvent.OnSetMood(it)) }
                )
            }
        )
    }
}