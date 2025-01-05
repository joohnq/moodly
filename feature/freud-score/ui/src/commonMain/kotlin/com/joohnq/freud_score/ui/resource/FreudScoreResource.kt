package com.joohnq.freud_score.ui.resource

import com.joohnq.freud_score.domain.entity.FreudScorePalette
import com.joohnq.freud_score.domain.property.FreudScoreProperties
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.at_risk
import com.joohnq.shared.ui.healthy
import com.joohnq.shared.ui.mostly_healthy
import com.joohnq.shared.ui.stable
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.unhealthy
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
            backgroundColor = Colors.Green50
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
            backgroundColor = Colors.Yellow50
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
            backgroundColor = Colors.Brown70
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
            backgroundColor = Colors.Orange40
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
            backgroundColor = Colors.Purple40
        )
    )
}