package com.joohnq.stress_level.overview.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.impl.ui.component.StressLevelHistory
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.getTodayStressLevelRecord
import com.joohnq.stress_level.overview.component.StressLevelOverviewInsight
import com.joohnq.stress_level.overview.component.StressLevelOverviewPanel
import com.joohnq.stress_level.overview.component.StressLevelOverviewTriggers
import com.joohnq.ui.mapper.UiStateMapper.foldComposable

@Composable
fun StressLevelOverviewContent(
    state: StressLevelOverviewContract.State,
    onIntent: (StressLevelOverviewContract.Intent) -> Unit = {},
    onEvent: (StressLevelOverviewContract.Event) -> Unit = {},
) {
    state.records.foldComposable(
        onSuccess = { records ->
            val record = records.getTodayStressLevelRecord()
            val hasToday = record != null

            ConvexGroupLazyLayout(
                containerColor = Colors.White,
                isDark = !hasToday,
                image = Drawables.Images.StressLevelBackground,
                color = if (hasToday) record.stressLevel.palette.imageColor else Colors.Brown10,
                panelBackgroundColor = if (hasToday) record.stressLevel.palette.color else Colors.Brown10,
                panel = { modifier ->
                    VerticalSpacer(10.dp)
                    StressLevelOverviewPanel(
                        record = record
                    )
                },
                onAddButton = { onEvent(StressLevelOverviewContract.Event.navigateToAddStressLevel) },
                onGoBack = { onEvent(StressLevelOverviewContract.Event.GoBack) },
                body = { modifier ->
                    StressLevelOverviewTriggers(
                        modifier = modifier,
                        records = records,
                        onAddStressLevel = { onEvent(StressLevelOverviewContract.Event.navigateToAddStressLevel) }
                    )
                    StressLevelOverviewInsight(
                        modifier = modifier,
                        records = records,
                        onCreate = { onEvent(StressLevelOverviewContract.Event.navigateToAddStressLevel) }
                    )
                    StressLevelHistory(
                        modifier = modifier,
                        records = records.take(7),
                        onDelete = { id -> onIntent(StressLevelOverviewContract.Intent.Delete(id)) },
                        onAddStressLevel = { onEvent(StressLevelOverviewContract.Event.navigateToAddStressLevel) }
                    )
                }
            )
        }
    )
}