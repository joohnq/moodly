package com.joohnq.moodapp.entities

import com.joohnq.moodapp.entities.palette.FreudScorePalette
import com.joohnq.moodapp.view.constants.Colors
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.at_risk
import moodapp.composeapp.generated.resources.healthy
import moodapp.composeapp.generated.resources.mostly_healthy
import moodapp.composeapp.generated.resources.stable
import moodapp.composeapp.generated.resources.unhealthy
import org.jetbrains.compose.resources.StringResource

sealed class FreudScore(
    open val score: Int,
    val title: StringResource,
) {
    data class Healthy(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.healthy,
    )

    data class MostlyHealthy(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.mostly_healthy,
    )

    data class Stable(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.stable,
    )

    data class AtRisk(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.at_risk,
    )

    data class Unhealthy(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = Res.string.unhealthy,
    )

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

        fun init(): FreudScore = Stable(50)

        fun getPalette(value: FreudScore): FreudScorePalette = when (value) {
            is Healthy -> FreudScorePalette(
                color = Colors.Green10,
                subColor = Colors.Green40,
                backgroundColor = Colors.Green50
            )

            is MostlyHealthy -> FreudScorePalette(
                color = Colors.Yellow10,
                subColor = Colors.Yellow40,
                backgroundColor = Colors.Yellow50
            )

            is Stable -> FreudScorePalette(
                color = Colors.Brown20,
                subColor = Colors.Brown40,
                backgroundColor = Colors.Brown70
            )

            is AtRisk -> FreudScorePalette(
                color = Colors.Orange10,
                subColor = Colors.Orange30,
                backgroundColor = Colors.Orange40
            )

            is Unhealthy -> FreudScorePalette(
                color = Colors.Purple10,
                subColor = Colors.Purple30,
                backgroundColor = Colors.Purple40
            )
        }
    }
}

