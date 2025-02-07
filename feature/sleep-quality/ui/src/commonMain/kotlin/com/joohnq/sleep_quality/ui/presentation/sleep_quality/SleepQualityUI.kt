package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.mapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.ui.components.SleepContent
import com.joohnq.sleep_quality.ui.components.SleepPanel
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityState
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
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
            val resource = record?.sleepQuality?.toResource()
            val hasToday = resource != null

            DecoratedConvexPanelList(
                containerColor = Colors.White,
                isDark = !hasToday,
                image = Drawables.Images.SleepQualityBackground,
                color = if (hasToday) resource.palette.imageColor else Colors.Brown10,
                panelBackgroundColor = if (hasToday) resource.palette.color else Colors.Brown10,
                panel = { modifier ->
                    SleepPanel(
                        modifier = modifier,
                        record = record,
                        resource = resource,
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
                    SleepQualityRecord(
                        sleepQuality = SleepQuality.Worst
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
                    SleepQualityRecord(
                        sleepQuality = SleepQuality.Poor
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
                    SleepQualityRecord(
                        sleepQuality = SleepQuality.Fair
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
                    SleepQualityRecord(
                        sleepQuality = SleepQuality.Good
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
                    SleepQualityRecord(
                        sleepQuality = SleepQuality.Excellent
                    )
                )
            )
        ),
    )
}