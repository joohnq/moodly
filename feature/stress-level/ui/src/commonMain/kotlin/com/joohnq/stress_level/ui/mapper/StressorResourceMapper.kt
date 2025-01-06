package com.joohnq.stress_level.ui.mapper

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.ui.resource.StressorResource
import com.joohnq.stress_level.ui.resource.StressorResource.Finances
import com.joohnq.stress_level.ui.resource.StressorResource.InPeace
import com.joohnq.stress_level.ui.resource.StressorResource.Kids
import com.joohnq.stress_level.ui.resource.StressorResource.Life
import com.joohnq.stress_level.ui.resource.StressorResource.Loneliness
import com.joohnq.stress_level.ui.resource.StressorResource.Other
import com.joohnq.stress_level.ui.resource.StressorResource.Relationship
import com.joohnq.stress_level.ui.resource.StressorResource.Work
import org.jetbrains.compose.resources.stringResource

fun getAllStressorResource(): List<StressorResource> =
    listOf(Work, Relationship, Kids, Life, Finances, Loneliness, Other())

fun Stressor.toResource(): StressorResource = when (this) {
    Stressor.Work -> Work
    Stressor.Relationship -> Relationship
    Stressor.Kids -> Kids
    Stressor.Life -> Life
    Stressor.Finances -> Finances
    Stressor.Loneliness -> Loneliness
    Stressor.InPeace -> InPeace
    is Stressor.Other -> Other(this.other)
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
    is Other -> Stressor.Other(other)
}

fun List<StressorResource>.toDomain(): List<Stressor> = map { it.toDomain() }

@Composable fun List<StressorResource>.getText(): String =
    map { stressor ->
        if (stressor is Other) {
            stressor.other.replaceFirstChar { it.uppercase() }
        } else {
            stringResource(stressor.text)
        }
    }.joinToString(separator = ", ")