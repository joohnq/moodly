package com.joohnq.moodapp.model.entities

sealed class FreudScore(open val score: Int, val title: String) {
    data class Healthy(
        override val score: Int,
    ) : FreudScore(score, "Healthy")

    data class MostlyHealthy(
        override val score: Int,
    ) : FreudScore(score, "Mostly Healthy")

    data class Stable(
        override val score: Int,
    ) : FreudScore(score, "Stable")

    data class AtRisk(
        override val score: Int,
    ) : FreudScore(score, "At - Risk")

    data class Unhealthy(
        override val score: Int,
    ) : FreudScore(score, "Unhealthy")

    companion object {
        fun fromScore(score: Int): FreudScore {
            return when (score) {
                in 0..19 -> Unhealthy(score)
                in 20..39 -> AtRisk(score)
                in 40..59 -> Stable(score)
                in 60..79 -> MostlyHealthy(score)
                in 80..100 -> Healthy(score)
                else -> throw IllegalArgumentException("Unknown freud score: $score")
            }
        }
    }
}

