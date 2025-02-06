package com.joohnq.freud_score.domain.mapper

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.freud_score.domain.entity.FreudScore.AtRisk
import com.joohnq.freud_score.domain.entity.FreudScore.Healthy
import com.joohnq.freud_score.domain.entity.FreudScore.MostlyHealthy
import com.joohnq.freud_score.domain.entity.FreudScore.Stable
import com.joohnq.freud_score.domain.entity.FreudScore.Unhealthy

fun Int.toFreudScore(): FreudScore {
    return when (this) {
        in 0..20 -> Unhealthy(this)
        in 21..40 -> AtRisk(this)
        in 41..60 -> Stable(this)
        in 61..80 -> MostlyHealthy(this)
        in 81..100 -> Healthy(this)
        else -> throw IllegalArgumentException("Unknown freud score: $this")
    }
}

fun init(): FreudScore = Stable(50)

