package com.joohnq.stress_level.ui

import androidx.compose.runtime.Composable
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.finances
import com.joohnq.shared.ui.in_peace
import com.joohnq.shared.ui.kids
import com.joohnq.shared.ui.life
import com.joohnq.shared.ui.loneliness
import com.joohnq.shared.ui.other
import com.joohnq.shared.ui.relationship
import com.joohnq.shared.ui.work
import com.joohnq.stress_level.domain.StressorProperties
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.domain.entity.Stressor.Companion.FINANCES
import com.joohnq.stress_level.domain.entity.Stressor.Companion.IN_PEACE
import com.joohnq.stress_level.domain.entity.Stressor.Companion.KIDS
import com.joohnq.stress_level.domain.entity.Stressor.Companion.LIFE
import com.joohnq.stress_level.domain.entity.Stressor.Companion.LONELINESS
import com.joohnq.stress_level.domain.entity.Stressor.Companion.RELATIONSHIP
import com.joohnq.stress_level.domain.entity.Stressor.Companion.WORK
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed class StressorResource(
    val text: StringResource,
    override val id: String,
) : StressorProperties {
    data object Work : StressorResource(text = Res.string.work, id = WORK.id)
    data object Relationship :
        StressorResource(text = Res.string.relationship, id = RELATIONSHIP.id)

    data object Kids : StressorResource(text = Res.string.kids, id = KIDS.id)
    data object Life : StressorResource(text = Res.string.life, id = LIFE.id)
    data object Finances : StressorResource(text = Res.string.finances, id = FINANCES.id)
    data object Loneliness : StressorResource(text = Res.string.loneliness, id = LONELINESS.id)
    data object InPeace : StressorResource(text = Res.string.in_peace, id = IN_PEACE.id)
    data class Other(val other: String = "") : StressorResource(text = Res.string.other, id = other)

    companion object {
        fun getAll(): List<StressorResource> =
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

        @Composable fun getText(stressors: List<StressorResource>): String =
            stressors.map { stressor ->
                if (stressor is Other) {
                    stressor.other.replaceFirstChar { it.uppercase() }
                } else {
                    stringResource(stressor.text)
                }
            }.joinToString(separator = ", ")
    }
}