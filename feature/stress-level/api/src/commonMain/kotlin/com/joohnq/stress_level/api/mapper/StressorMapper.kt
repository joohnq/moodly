package com.joohnq.stress_level.api.mapper

import com.joohnq.stress_level.api.entity.Stressor
import com.joohnq.stress_level.api.entity.Stressor.Companion.FINANCES
import com.joohnq.stress_level.api.entity.Stressor.Companion.IN_PEACE
import com.joohnq.stress_level.api.entity.Stressor.Companion.KIDS
import com.joohnq.stress_level.api.entity.Stressor.Companion.LIFE
import com.joohnq.stress_level.api.entity.Stressor.Companion.LONELINESS
import com.joohnq.stress_level.api.entity.Stressor.Companion.OTHER
import com.joohnq.stress_level.api.entity.Stressor.Companion.RELATIONSHIP
import com.joohnq.stress_level.api.entity.Stressor.Companion.WORK

object StressorMapper {
    fun Long.toStressor(): Stressor =
        when (this) {
            WORK.id -> Stressor.Work
            RELATIONSHIP.id -> Stressor.Relationship
            KIDS.id -> Stressor.Kids
            LIFE.id -> Stressor.Life
            LONELINESS.id -> Stressor.Loneliness
            FINANCES.id -> Stressor.Finances
            IN_PEACE.id -> Stressor.InPeace
            OTHER.id -> Stressor.Other
            else -> throw IllegalArgumentException("Unknown stressor: $this")
        }

    fun Stressor?.toString(): String = this?.id.toString()

    fun List<Stressor>.join(): List<String> = this.map { it.id.toString() }

    fun List<String>.toStressors(): List<Stressor> = this.map { it.toLong().toStressor() }
}
