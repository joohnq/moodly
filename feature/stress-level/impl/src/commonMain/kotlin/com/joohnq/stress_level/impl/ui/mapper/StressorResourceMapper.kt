package com.joohnq.stress_level.impl.ui.mapper

import androidx.compose.ui.graphics.Color
import com.joohnq.stress_level.api.entity.Stressor
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource.Finances
import com.joohnq.stress_level.impl.ui.resource.StressorResource.InPeace
import com.joohnq.stress_level.impl.ui.resource.StressorResource.Kids
import com.joohnq.stress_level.impl.ui.resource.StressorResource.Life
import com.joohnq.stress_level.impl.ui.resource.StressorResource.Loneliness
import com.joohnq.stress_level.impl.ui.resource.StressorResource.Other
import com.joohnq.stress_level.impl.ui.resource.StressorResource.Relationship
import com.joohnq.stress_level.impl.ui.resource.StressorResource.Work

object StressorResourceMapper {
    fun allStressorResource(): List<StressorResource> =
        listOf(
            Work,
            Relationship,
            Kids,
            Life,
            Finances,
            Loneliness,
            Other
        )

    fun Stressor.toResource(): StressorResource =
        when (this) {
            Stressor.Work -> Work
            Stressor.Relationship -> Relationship
            Stressor.Kids -> Kids
            Stressor.Life -> Life
            Stressor.Finances -> Finances
            Stressor.Loneliness -> Loneliness
            Stressor.InPeace -> InPeace
            Stressor.Other -> Other
        }

    fun List<Stressor>.toResource(): List<StressorResource> = map { it.toResource() }

    fun StressorResource.toDomain(): Stressor =
        when (this) {
            Work -> Stressor.Work
            Relationship -> Stressor.Relationship
            Kids -> Stressor.Kids
            Life -> Stressor.Life
            Finances -> Stressor.Finances
            Loneliness -> Stressor.Loneliness
            InPeace -> Stressor.InPeace
            Other -> Stressor.Other
        }

    fun List<StressorResource>.toMap(): List<Pair<StressorResource, Int>> =
        groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }

    fun List<Pair<StressorResource, Int>>.toSegments(size: Int): List<Pair<Color, Float>> =
        map { (stressor, count) ->
            val percent = (count.toDouble() / size) * 100
            stressor.color to percent.toFloat()
        }

    fun List<StressorResource>.toDomain(): List<Stressor> = map { it.toDomain() }
}
