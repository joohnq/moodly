package com.joohnq.stress_level.impl.ui.mapper

import com.joohnq.stress_level.api.entity.Stressor
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource.*

fun getAllStressorResource(): List<StressorResource> =
    listOf(Work, Relationship, Kids, Life, Finances, Loneliness, Other)

fun Stressor.toResource(): StressorResource = when (this) {
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

fun StressorResource.toDomain(): Stressor = when (this) {
    Work -> Stressor.Work
    Relationship -> Stressor.Relationship
    Kids -> Stressor.Kids
    Life -> Stressor.Life
    Finances -> Stressor.Finances
    Loneliness -> Stressor.Loneliness
    InPeace -> Stressor.InPeace
    Other -> Stressor.Other
}

fun List<StressorResource>.toDomain(): List<Stressor> = map { it.toDomain() }
