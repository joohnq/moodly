package com.joohnq.stress_level.ui.mapper

import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.stress_level.ui.resource.StressLevelResource.Five
import com.joohnq.stress_level.ui.resource.StressLevelResource.Four
import com.joohnq.stress_level.ui.resource.StressLevelResource.One
import com.joohnq.stress_level.ui.resource.StressLevelResource.Three
import com.joohnq.stress_level.ui.resource.StressLevelResource.Two

fun getAllStressLevelResource(): List<StressLevelResource> = listOf(
    One,
    Two,
    Three,
    Four,
    Five
)

fun getBrushGradient(i: Int): List<Color> = listOf(
    listOf(Colors.Green50, Colors.Green50),
    listOf(Colors.Green50, Colors.Yellow50),
    listOf(Colors.Yellow50, Colors.Yellow50),
    listOf(Colors.Yellow50, Colors.Orange50),
    listOf(Colors.Orange50, Colors.Orange50)
)[i]

fun StressLevel.toResource(): StressLevelResource = when (this) {
    StressLevel.One -> One
    StressLevel.Two -> Two
    StressLevel.Three -> Three
    StressLevel.Four -> Four
    StressLevel.Five -> Five
}

fun StressLevelResource.toDomain(): StressLevel = when (this) {
    One -> StressLevel.One
    Two -> StressLevel.Two
    Three -> StressLevel.Three
    Four -> StressLevel.Four
    Five -> StressLevel.Five
}

fun Float.fromSliderValueToStressLevelResource(): StressLevelResource = when (this) {
    0f -> One
    25f -> Two
    50f -> Three
    75f -> Four
    else -> Five
}