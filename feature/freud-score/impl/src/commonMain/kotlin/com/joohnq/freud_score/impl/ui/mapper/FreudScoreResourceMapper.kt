package com.joohnq.freud_score.impl.ui.mapper

import com.joohnq.freud_score.api.entity.FreudScore
import com.joohnq.freud_score.impl.ui.resource.FreudScoreResource

object FreudScoreResourceMapper {
    fun FreudScore.toResource(): FreudScoreResource =
        when (this) {
            is FreudScore.Healthy -> FreudScoreResource.Healthy(score)
            is FreudScore.MostlyHealthy -> FreudScoreResource.MostlyHealthy(score)
            is FreudScore.Stable -> FreudScoreResource.Stable(score)
            is FreudScore.AtRisk -> FreudScoreResource.AtRisk(score)
            is FreudScore.Unhealthy -> FreudScoreResource.Unhealthy(score)
            FreudScore.NotAvailable -> FreudScoreResource.NotAvailable
        }

    fun allFreudScoreResources(score: Int): List<FreudScoreResource> =
        listOf(
            FreudScoreResource.Healthy(score),
            FreudScoreResource.MostlyHealthy(score),
            FreudScoreResource.Stable(score),
            FreudScoreResource.AtRisk(score),
            FreudScoreResource.Unhealthy(score)
        )

    fun Int.toInitialFreudScore(): Int =
        when (this) {
            0 -> 81
            1 -> 61
            2 -> 41
            3 -> 21
            4 -> 1
            else -> throw IllegalArgumentException("Unknown freud score: $this")
        }

    fun Int.toEndFreudScore(): Int =
        when (this) {
            0 -> 100
            1 -> 80
            2 -> 60
            3 -> 40
            4 -> 20
            else -> throw IllegalArgumentException("Unknown freud score: $this")
        }
}
