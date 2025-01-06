package com.joohnq.stress_level.domain.mapper

import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.domain.entity.Stressor.Companion.FINANCES
import com.joohnq.stress_level.domain.entity.Stressor.Companion.IN_PEACE
import com.joohnq.stress_level.domain.entity.Stressor.Companion.KIDS
import com.joohnq.stress_level.domain.entity.Stressor.Companion.LIFE
import com.joohnq.stress_level.domain.entity.Stressor.Companion.LONELINESS
import com.joohnq.stress_level.domain.entity.Stressor.Companion.RELATIONSHIP
import com.joohnq.stress_level.domain.entity.Stressor.Companion.WORK
import com.joohnq.stress_level.domain.entity.Stressor.Finances
import com.joohnq.stress_level.domain.entity.Stressor.InPeace
import com.joohnq.stress_level.domain.entity.Stressor.Kids
import com.joohnq.stress_level.domain.entity.Stressor.Life
import com.joohnq.stress_level.domain.entity.Stressor.Loneliness
import com.joohnq.stress_level.domain.entity.Stressor.Other
import com.joohnq.stress_level.domain.entity.Stressor.Relationship
import com.joohnq.stress_level.domain.entity.Stressor.Work

fun String.toStressor(): Stressor = when (this) {
    WORK.id -> Work
    RELATIONSHIP.id -> Relationship
    KIDS.id -> Kids
    LIFE.id -> Life
    LONELINESS.id -> Loneliness
    FINANCES.id -> Finances
    IN_PEACE.id -> InPeace
    else -> Other(this)
}

fun Stressor?.toString(): String = this?.id ?: ""

fun getAllStressor(): List<Stressor> =
    listOf(Work, Relationship, Kids, Life, Finances, Loneliness, Other())

fun List<Stressor>.containOther(): Boolean =
    any { it::class == Other::class }