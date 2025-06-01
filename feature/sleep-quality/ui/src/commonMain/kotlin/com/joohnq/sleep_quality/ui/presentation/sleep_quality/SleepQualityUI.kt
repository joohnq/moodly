package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.mapper.handle
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.ui.component.SleepContent
import com.joohnq.sleep_quality.ui.component.SleepPanel
import com.joohnq.sleep_quality.ui.resource.mapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.viewmodel.SleepQualityContract
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepQualityUI(
    state: SleepQualityContract.State,
    onEvent: (SleepQualityContract.Event) -> Unit = {},
    onIntent: (SleepQualityContract.Intent) -> Unit = {},
) {
    state.records.handle(
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
                onAddButton = { onEvent(SleepQualityContract.Event.NavigateToAddSleepQuality) },
                onGoBack = { onEvent(SleepQualityContract.Event.GoBack) },
                content = { modifier ->
                    SleepContent(
                        modifier = modifier,
                        records = records,
                        onEvent = onEvent,
                        onIntent = onIntent
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
        state = SleepQualityContract.State(
            records = UiState.Success(
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
        state = SleepQualityContract.State(
            records = UiState.Success(
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
        state = SleepQualityContract.State(
            records = UiState.Success(
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
        state = SleepQualityContract.State(
            records = UiState.Success(
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
        state = SleepQualityContract.State(
            records = UiState.Success(
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
        state = SleepQualityContract.State(
            records = UiState.Success(
                listOf(
                    SleepQualityRecordResource(
                        sleepQuality = SleepQualityResource.Excellent
                    )
                )
            )
        ),
    )
}