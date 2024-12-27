package com.joohnq.freud_score.domain.entity

import com.joohnq.mood.ui.theme.Colors
import moodapp.composeapp.generated.resources.at_risk
import moodapp.composeapp.generated.resources.healthy
import moodapp.composeapp.generated.resources.mostly_healthy
import moodapp.composeapp.generated.resources.stable
import moodapp.composeapp.generated.resources.unhealthy
import moodapp.feature.freud_score.domain.generated.resources.Res
import org.jetbrains.compose.resources.StringResource

sealed class FreudScore(
    open val score: Int,
    val title: StringResource,
    val palette: FreudScorePalette
) {
    data class Healthy(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.healthy,
        palette = FreudScorePalette(
            color = Colors.Green10,
            subColor = Colors.Green40,
            backgroundColor = Colors.Green50
        )
    )

    data class MostlyHealthy(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.mostly_healthy,
        palette = FreudScorePalette(
            color = Colors.Yellow10,
            subColor = Colors.Yellow40,
            backgroundColor = Colors.Yellow50
        )
    )

    data class Stable(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.stable,
        palette = FreudScorePalette(
            color = Colors.Brown20,
            subColor = Colors.Brown40,
            backgroundColor = Colors.Brown70
        )
    )

    data class AtRisk(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.at_risk,
        palette = FreudScorePalette(
            color = Colors.Orange10,
            subColor = Colors.Orange30,
            backgroundColor = Colors.Orange40
        )
    )

    data class Unhealthy(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.unhealthy,
        palette = FreudScorePalette(
            color = Colors.Purple10,
            subColor = Colors.Purple30,
            backgroundColor = Colors.Purple40
        )
    )

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

