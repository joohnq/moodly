package com.joohnq.freud_score.domain.entity

import com.joohnq.freud_score.domain.FreudScoreProperties

sealed class FreudScore(
    override val score: Int,
) : FreudScoreProperties {
    data class Healthy(override val score: Int) : FreudScore(score)
    data class MostlyHealthy(override val score: Int) : FreudScore(score)
    data class Stable(override val score: Int) : FreudScore(score)
    data class AtRisk(override val score: Int) : FreudScore(score)
    data class Unhealthy(override val score: Int) : FreudScore(score)

    companion object {
        fun fromScore(score: Int): FreudScore {
            return when (score) {
                in 0..20 -> Unhealthy(score)
                in 21..40 -> AtRisk(score)
                in 41..60 -> Stable(score)
                in 61..80 -> MostlyHealthy(score)
                in 81..100 -> Healthy(score)
                else -> throw IllegalArgumentException("Unknown freud score: $score")
            }
        }

        fun init(): FreudScore = Stable(50)
    }
}


