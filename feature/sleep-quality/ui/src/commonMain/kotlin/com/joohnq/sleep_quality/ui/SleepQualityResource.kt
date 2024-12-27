package com.joohnq.sleep_quality.ui

import com.joohnq.mood.theme.Colors
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.excellent
import com.joohnq.shared.ui.fair
import com.joohnq.shared.ui.five_hours
import com.joohnq.shared.ui.good
import com.joohnq.shared.ui.minus_three_hours
import com.joohnq.shared.ui.poor
import com.joohnq.shared.ui.seven_nine_hours
import com.joohnq.shared.ui.six_seven_hours
import com.joohnq.shared.ui.three_four_hours
import com.joohnq.shared.ui.worst
import com.joohnq.sleep_quality.domain.SleepQualityProperties
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQualityPalette
import org.jetbrains.compose.resources.StringResource

sealed class SleepQualityResource(
    override val id: Int,
    override val level: Int,
    val firstText: StringResource,
    val secondText: StringResource,
    val palette: SleepQualityPalette
) : SleepQualityProperties {

    data object Excellent : SleepQualityResource(
        id = SleepQuality.EXCELLENT.id,
        level = SleepQuality.EXCELLENT.level,
        firstText = Res.string.excellent,
        secondText = Res.string.seven_nine_hours,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Green10,
            backgroundColor = Colors.Green30,
            color = Colors.Green60
        )
    )

    data object Good : SleepQualityResource(
        id = SleepQuality.GOOD.id,
        level = SleepQuality.GOOD.level,
        firstText = Res.string.good,
        secondText = Res.string.six_seven_hours,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Yellow10,
            backgroundColor = Colors.Yellow30,
            color = Colors.Yellow60
        )
    )

    data object Fair : SleepQualityResource(
        id = SleepQuality.FAIR.id,
        level = SleepQuality.FAIR.level,
        firstText = Res.string.fair,
        secondText = Res.string.five_hours,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Brown20,
            backgroundColor = Colors.Brown40,
            color = Colors.Brown70
        )
    )

    data object Poor : SleepQualityResource(
        id = SleepQuality.POOR.id,
        level = SleepQuality.POOR.level,
        firstText = Res.string.poor,
        secondText = Res.string.three_four_hours,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Orange10,
            backgroundColor = Colors.Orange30,
            color = Colors.Orange50
        )
    )

    data object Worst : SleepQualityResource(
        id = SleepQuality.WORST.id,
        level = SleepQuality.WORST.level,
        firstText = Res.string.worst,
        secondText = Res.string.minus_three_hours,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Purple10,
            backgroundColor = Colors.Purple30,
            color = Colors.Purple50
        )
    )

    companion object {
        fun getAll(): List<SleepQualityResource> = listOf(
            Excellent,
            Good,
            Fair,
            Poor,
            Worst
        )

        fun SleepQuality.toResource(): SleepQualityResource = when (this) {
            SleepQuality.Excellent -> Excellent
            SleepQuality.Good -> Good
            SleepQuality.Fair -> Fair
            SleepQuality.Poor -> Poor
            SleepQuality.Worst -> Worst
        }

        fun SleepQualityResource.toDomain(): SleepQuality = when (this) {
            Excellent -> SleepQuality.Excellent
            Good -> SleepQuality.Good
            Fair -> SleepQuality.Fair
            Poor -> SleepQuality.Poor
            Worst -> SleepQuality.Worst
        }

        fun fromSliderValue(value: Float): SleepQualityResource = when (value) {
            0f -> Worst
            25f -> Poor
            50f -> Fair
            75f -> Good
            else -> Excellent
        }
    }
}