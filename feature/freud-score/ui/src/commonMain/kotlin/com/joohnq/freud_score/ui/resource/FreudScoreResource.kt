package com.joohnq.freud_score.ui.resource

import com.joohnq.freud_score.domain.entity.FreudScorePalette
import com.joohnq.freud_score.domain.property.FreudScoreProperties
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.at_risk
import com.joohnq.shared_resources.healthy
import com.joohnq.shared_resources.mostly_healthy
import com.joohnq.shared_resources.stable
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.unhealthy
import org.jetbrains.compose.resources.StringResource

sealed class FreudScoreResource(
    override val score: Int,
    val title: StringResource,
    val palette: FreudScorePalette,
) : FreudScoreProperties {
    data class Healthy(
        override val score: Int,
    ) : FreudScoreResource(
        score = score,
        title = Res.string.healthy,
        palette = FreudScorePalette(
            color = Colors.Green10,
            subColor = Colors.Green40,
            backgroundColor = Colors.Green50,
            imageColor = Colors.Green70
        )
    )

    data class MostlyHealthy(
        override val score: Int,
    ) : FreudScoreResource(
        score = score,
        title = Res.string.mostly_healthy,
        palette = FreudScorePalette(
            color = Colors.Yellow10,
            subColor = Colors.Yellow40,
            backgroundColor = Colors.Yellow50,
            imageColor = Colors.Yellow60
        )
    )

    data class Stable(
        override val score: Int,
    ) : FreudScoreResource(
        score = score,
        title = Res.string.stable,
        palette = FreudScorePalette(
            color = Colors.Brown20,
            subColor = Colors.Brown40,
            backgroundColor = Colors.Brown60,
            imageColor = Colors.Brown70
        )
    )

    data class AtRisk(
        override val score: Int,
    ) : FreudScoreResource(
        score = score,
        title = Res.string.at_risk,
        palette = FreudScorePalette(
            color = Colors.Orange10,
            subColor = Colors.Orange30,
            backgroundColor = Colors.Orange40,
            imageColor = Colors.Orange50,
        )
    )

    data class Unhealthy(
        override val score: Int,
    ) : FreudScoreResource(
        score = score,
        title = Res.string.unhealthy,
        palette = FreudScorePalette(
            color = Colors.Purple10,
            subColor = Colors.Purple30,
            backgroundColor = Colors.Purple40,
            imageColor = Colors.Purple60
        )
    )
}