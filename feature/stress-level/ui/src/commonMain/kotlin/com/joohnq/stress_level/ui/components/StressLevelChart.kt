package com.joohnq.stress_level.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.mood.domain.use_case.OrganizeStatRangeUseCase
import com.joohnq.shared.ui.components.CustomChart
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.mapper.toPercent
import com.joohnq.stress_level.ui.mapper.toResource
import org.koin.compose.koinInject

@Composable
fun StressLevelChart(stressLevelRecords: List<StressLevelRecord>) {
    val first = stressLevelRecords.last().stressLevel
    val resource = first.toResource()
    val organizeStatRangeUseCase: OrganizeStatRangeUseCase = koinInject()
    val values =
        remember {
            organizeStatRangeUseCase(stressLevelRecords.map {
                it.stressLevel.level.toPercent()
            })
        }
    CustomChart(
        color = resource.palette.color,
        values = values
    )
}