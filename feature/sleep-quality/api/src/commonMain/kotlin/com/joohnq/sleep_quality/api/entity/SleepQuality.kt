package com.joohnq.sleep_quality.api.entity

import com.joohnq.sleep_quality.api.property.SleepQualityProperties

sealed class SleepQuality(
    override val id: Int,
    override val level: Int,
) : SleepQualityProperties {
    data object Excellent : SleepQuality(EXCELLENT.id, EXCELLENT.level)
    data object Good : SleepQuality(GOOD.id, GOOD.level)
    data object Fair : SleepQuality(FAIR.id, FAIR.level)
    data object Poor : SleepQuality(POOR.id, POOR.level)
    data object Worst : SleepQuality(WORST.id, WORST.level)

    companion object {
        val EXCELLENT = DSleepQualityProperties(5, 5)
        val GOOD = DSleepQualityProperties(4, 4)
        val FAIR = DSleepQualityProperties(3, 3)
        val POOR = DSleepQualityProperties(2, 2)
        val WORST = DSleepQualityProperties(1, 1)
    }
}


