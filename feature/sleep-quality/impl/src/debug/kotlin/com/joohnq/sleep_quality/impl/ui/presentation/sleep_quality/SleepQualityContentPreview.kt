package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import com.joohnq.ui.entity.UiState
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
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