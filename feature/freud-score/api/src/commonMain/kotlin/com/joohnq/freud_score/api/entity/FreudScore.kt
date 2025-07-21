package com.joohnq.freud_score.api.entity

import com.joohnq.freud_score.api.property.FreudScoreProperties

sealed class FreudScore(
    override val score: Int,
) : FreudScoreProperties {
    data class Healthy(override val score: Int) : FreudScore(score)
    data class MostlyHealthy(override val score: Int) : FreudScore(score)
    data class Stable(override val score: Int) : FreudScore(score)
    data class AtRisk(override val score: Int) : FreudScore(score)
    data class Unhealthy(override val score: Int) : FreudScore(score)
    data object NotAvailable : FreudScore(0)
}


