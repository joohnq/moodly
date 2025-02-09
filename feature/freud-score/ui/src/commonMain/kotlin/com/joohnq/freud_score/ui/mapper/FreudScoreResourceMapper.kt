package com.joohnq.freud_score.ui.mapper

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.freud_score.ui.resource.FreudScoreResource.*

typealias Index = Int

fun FreudScore.toResource(): FreudScoreResource =
    when (this) {
        is FreudScore.Healthy -> Healthy(score)
        is FreudScore.MostlyHealthy -> MostlyHealthy(score)
        is FreudScore.Stable -> Stable(score)
        is FreudScore.AtRisk -> AtRisk(score)
        is FreudScore.Unhealthy -> Unhealthy(score)
    }

fun getAllFreudScoreResources(score: Int): List<FreudScoreResource> = listOf(
    Healthy(score),
    MostlyHealthy(score),
    Stable(score),
    AtRisk(score),
    Unhealthy(score),
)

fun Index.toInitialFreudScore(): Int =
    when (this) {
        0 -> 81
        1 -> 61
        2 -> 41
        3 -> 21
        4 -> 0
        else -> throw IllegalArgumentException("Unknown freud score: $this")
    }

fun Index.toEndFreudScore(): Int =
    when (this) {
        0 -> 100
        1 -> 80
        2 -> 60
        3 -> 40
        4 -> 20
        else -> throw IllegalArgumentException("Unknown freud score: $this")
    }