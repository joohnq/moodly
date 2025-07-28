package com.joohnq.stress_level.impl.ui.mapper

import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.stress_level.api.entity.StressLevel
import com.joohnq.stress_level.impl.ui.resource.StressLevelResource

fun getAllStressLevelResource(): List<StressLevelResource> =
    listOf(
        StressLevelResource.One,
        StressLevelResource.Two,
        StressLevelResource.Three,
        StressLevelResource.Four,
        StressLevelResource.Five
    )

fun getBrushGradient(i: Int): List<Color> =
    listOf(
        listOf(Colors.Green50, Colors.Green50),
        listOf(Colors.Green50, Colors.Yellow50),
        listOf(Colors.Yellow50, Colors.Yellow50),
        listOf(Colors.Yellow50, Colors.Orange50),
        listOf(Colors.Orange50, Colors.Orange50)
    )[i]

fun StressLevel.toResource(): StressLevelResource =
    when (this) {
        StressLevel.One -> StressLevelResource.One
        StressLevel.Two -> StressLevelResource.Two
        StressLevel.Three -> StressLevelResource.Three
        StressLevel.Four -> StressLevelResource.Four
        StressLevel.Five -> StressLevelResource.Five
    }

fun StressLevelResource.toDomain(): StressLevel =
    when (this) {
        StressLevelResource.One -> StressLevel.One
        StressLevelResource.Two -> StressLevel.Two
        StressLevelResource.Three -> StressLevel.Three
        StressLevelResource.Four -> StressLevel.Four
        StressLevelResource.Five -> StressLevel.Five
    }

fun Float.fromSliderValueToStressLevelResource(): StressLevelResource =
    when (this) {
        0f -> StressLevelResource.One
        25f -> StressLevelResource.Two
        50f -> StressLevelResource.Three
        75f -> StressLevelResource.Four
        else -> StressLevelResource.Five
    }
