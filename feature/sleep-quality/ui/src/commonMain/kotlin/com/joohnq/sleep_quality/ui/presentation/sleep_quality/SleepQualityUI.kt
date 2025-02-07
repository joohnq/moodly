package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.ui.components.SleepContent
import com.joohnq.sleep_quality.ui.components.SleepPanel
import com.joohnq.sleep_quality.ui.mapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityState
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SleepQualityUI(
    state: SleepQualityState,
    onEvent: (SleepQualityEvent) -> Unit = {},
) {
    state.sleepQualityRecords.foldComposable(
        onSuccess = { records ->
            val record = records.getTodaySleepQualityRecord()
            val hasToday = record != null

            DecoratedConvexPanelList(
                containerColor = Colors.White,
                isDark = !hasToday,
                image = Drawables.Images.SleepQualityBackground,
                color = if (hasToday) record.sleepQuality.palette.imageColor else Colors.Brown10,
                panelBackgroundColor = if (hasToday) record.sleepQuality.palette.color else Colors.Brown10,
                panel = { modifier ->
                    SleepPanel(
                        modifier = modifier,
                        record = record,
                    )
                },
                onAddButton = { onEvent(SleepQualityEvent.OnAddSleepQuality) },
                onGoBack = { onEvent(SleepQualityEvent.OnGoBack) },
                content = { modifier ->
                    SleepContent(
                        modifier = modifier,
                        records = records,
                        onSeeAll = {}
                    )
                }
            )
        }
    )
}

@Preview
@Composable
fun SleepQualityUIPreviewEmpty() {
    SleepQualityUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                )
            )
        ),
    )
}

@Preview
@Composable
fun SleepQualityUIPreviewWorst() {
    SleepQualityUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                    SleepQualityRecordResource(
                        sleepQuality = SleepQualityResource.Worst
                    )
                )
            )
        ),
    )
}

@Preview
@Composable
fun SleepQualityUIPreviewPoor() {
    SleepQualityUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                    SleepQualityRecordResource(
                        sleepQuality = SleepQualityResource.Poor
                    )
                )
            )
        ),
    )
}

@Preview
@Composable
fun SleepQualityUIPreviewFair() {
    SleepQualityUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                    SleepQualityRecordResource(
                        sleepQuality = SleepQualityResource.Fair
                    )
                )
            )
        ),
    )
}

@Preview
@Composable
fun SleepQualityUIPreviewGood() {
    SleepQualityUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                    SleepQualityRecordResource(
                        sleepQuality = SleepQualityResource.Good
                    )
                )
            )
        ),
    )
}

@Preview
@Composable
fun SleepQualityUIPreviewExcellent() {
    SleepQualityUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                    SleepQualityRecordResource(
                        sleepQuality = SleepQualityResource.Excellent
                    )
                )
            )
        ),
    )
}