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

fun Int.toStressor(): Stressor =
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
