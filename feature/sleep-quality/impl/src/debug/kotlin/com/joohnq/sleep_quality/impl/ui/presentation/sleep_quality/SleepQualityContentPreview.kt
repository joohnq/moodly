package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.foldComposable
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.impl.ui.component.SleepContent
import com.joohnq.sleep_quality.impl.ui.component.SleepPanel
import com.joohnq.sleep_quality.impl.ui.mapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SleepQualityContentPreview() {
    SleepQualityContent(
        state = SleepQualityState(
            records = UiState.Success(
                SleepQualityRecordResource.allSleepQualityRecordResource
            )
        ),
    )
}

@Preview
@Composable
fun SleepQualityContentEmptyPreview() {
    SleepQualityContent(
        state = SleepQualityState(
            records = UiState.Success(
                listOf(
                )
            )
        ),
    )
}