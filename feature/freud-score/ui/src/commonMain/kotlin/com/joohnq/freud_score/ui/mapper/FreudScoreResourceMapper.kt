package com.joohnq.freud_score.ui.mapper

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.freud_score.ui.resource.FreudScoreResource.AtRisk
import com.joohnq.freud_score.ui.resource.FreudScoreResource.Healthy
import com.joohnq.freud_score.ui.resource.FreudScoreResource.MostlyHealthy
import com.joohnq.freud_score.ui.resource.FreudScoreResource.Stable
import com.joohnq.freud_score.ui.resource.FreudScoreResource.Unhealthy

fun FreudScore.toResource(): FreudScoreResource =
    when (this) {
        is FreudScore.Healthy -> Healthy(score)
        is FreudScore.MostlyHealthy -> MostlyHealthy(score)
        is FreudScore.Stable -> Stable(score)
        is FreudScore.AtRisk -> AtRisk(score)
        is FreudScore.Unhealthy -> Unhealthy(score)
    }