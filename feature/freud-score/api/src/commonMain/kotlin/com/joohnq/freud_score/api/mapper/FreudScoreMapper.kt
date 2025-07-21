package com.joohnq.freud_score.api.mapper

import com.joohnq.freud_score.api.entity.FreudScore
import com.joohnq.freud_score.api.entity.FreudScore.AtRisk
import com.joohnq.freud_score.api.entity.FreudScore.Healthy
import com.joohnq.freud_score.api.entity.FreudScore.MostlyHealthy
import com.joohnq.freud_score.api.entity.FreudScore.Stable
import com.joohnq.freud_score.api.entity.FreudScore.Unhealthy

fun Int.toFreudScore(): FreudScore {
    return when (this) {
        in 1..20 -> Unhealthy(this)
        in 21..40 -> AtRisk(this)
        in 41..60 -> Stable(this)
        in 61..80 -> MostlyHealthy(this)
        in 81..100 -> Healthy(this)
        else -> FreudScore.NotAvailable
    }
}
