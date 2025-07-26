package com.joohnq.stress_level.api.mapper

import com.joohnq.stress_level.api.entity.Stressor
import com.joohnq.stress_level.api.entity.Stressor.*
import com.joohnq.stress_level.api.entity.Stressor.Companion.FINANCES
import com.joohnq.stress_level.api.entity.Stressor.Companion.IN_PEACE
import com.joohnq.stress_level.api.entity.Stressor.Companion.KIDS
import com.joohnq.stress_level.api.entity.Stressor.Companion.LIFE
import com.joohnq.stress_level.api.entity.Stressor.Companion.LONELINESS
import com.joohnq.stress_level.api.entity.Stressor.Companion.OTHER
import com.joohnq.stress_level.api.entity.Stressor.Companion.RELATIONSHIP
import com.joohnq.stress_level.api.entity.Stressor.Companion.WORK

fun Int.toStressor(): Stressor =
    when (this) {
        WORK.id -> Work
        RELATIONSHIP.id -> Relationship
        KIDS.id -> Kids
        LIFE.id -> Life
        LONELINESS.id -> Loneliness
        FINANCES.id -> Finances
        IN_PEACE.id -> InPeace
        OTHER.id -> Other
        else -> throw IllegalArgumentException("Unknown stressor: $this")
    }

fun Stressor?.toString(): String = this?.id.toString()
