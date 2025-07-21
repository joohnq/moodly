package com.joohnq.freud_score.impl.resource

import com.joohnq.freud_score.impl.entity.FreudScorePalette
import com.joohnq.freud_score.api.property.FreudScoreProperties
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.at_risk
import com.joohnq.shared_resources.certain_habits_or_conditions_are_putting_your_health_at_risk
import com.joohnq.shared_resources.healthy
import com.joohnq.shared_resources.mostly_healthy
import com.joohnq.shared_resources.not_available
import com.joohnq.shared_resources.stable
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.unhealthy
import com.joohnq.shared_resources.you_are_a_very_healthy_individual
import com.joohnq.shared_resources.you_maintain_good_health_habits
import com.joohnq.shared_resources.your_current_health_status_requires_immediate
import com.joohnq.shared_resources.your_health_is_in_a_balanced_state
import org.jetbrains.compose.resources.StringResource

sealed class FreudScoreResource(
    override val score: Int,
    val title: StringResource,
    val subtitle: StringResource,
    val palette: FreudScorePalette,
) : FreudScoreProperties {
    data class Healthy(
        override val score: Int,
    ) : FreudScoreResource(
        score = score,
        title = Res.string.healthy,
        subtitle = Res.string.you_are_a_very_healthy_individual,
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
        subtitle = Res.string.you_maintain_good_health_habits,
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
        subtitle = Res.string.your_health_is_in_a_balanced_state,
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
        subtitle = Res.string.certain_habits_or_conditions_are_putting_your_health_at_risk,
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
        subtitle = Res.string.your_current_health_status_requires_immediate,
        palette = FreudScorePalette(
            color = Colors.Purple10,
            subColor = Colors.Purple30,
            backgroundColor = Colors.Purple40,
            imageColor = Colors.Purple60
        )
    )

    data object NotAvailable : FreudScoreResource(
        score = 0,
        title = Res.string.not_available,
        subtitle = Res.string.not_available,
        palette = FreudScorePalette(
            color = Colors.Gray20,
            subColor = Colors.Gray40,
            backgroundColor = Colors.Gray60,
            imageColor = Colors.Gray70
        )
    )
}

