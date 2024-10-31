package com.joohnq.moodapp.model.entities

import androidx.compose.ui.graphics.Color
import com.joohnq.moodapp.view.constants.Colors

sealed class FreudScore(
    open val score: Int,
    val title: String,
    val color: Color,
    val subColor: Color,
    val backgroundColor: Color
) {
    data class Healthy(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = "Healthy",
        color = Colors.Green10,
        subColor = Colors.Green40,
        backgroundColor = Colors.Green50
    )

    data class MostlyHealthy(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = "Mostly Healthy",
        color = Colors.Yellow10,
        subColor = Colors.Yellow40,
        backgroundColor = Colors.Yellow50
    )

    data class Stable(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = "Stable",
        color = Colors.Brown20,
        subColor = Colors.Brown40,
        backgroundColor = Colors.Brown70
    )

    data class AtRisk(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = "At - Risk",
        color = Colors.Orange10,
        subColor = Colors.Orange30,
        backgroundColor = Colors.Orange40
    )

    data class Unhealthy(
        override val score: Int,
    ) : FreudScore(
        score = score,
        title = "Unhealthy",
        color = Colors.Purple10,
        subColor = Colors.Purple30,
        backgroundColor = Colors.Purple40
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
    }
}

