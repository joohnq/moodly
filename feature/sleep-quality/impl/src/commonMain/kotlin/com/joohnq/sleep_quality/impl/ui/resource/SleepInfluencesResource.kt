package com.joohnq.sleep_quality.impl.ui.resource

import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.alcohol_consumption
import com.joohnq.shared_resources.anxiety
import com.joohnq.shared_resources.caffeine
import com.joohnq.shared_resources.chill_sleep_environment
import com.joohnq.shared_resources.excessive_screen_time
import com.joohnq.shared_resources.exposure_to_natural_light
import com.joohnq.shared_resources.high_stress
import com.joohnq.shared_resources.meditation
import com.joohnq.shared_resources.physical_activity
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.ALCOHOL_CONSUMPTION
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.ANXIETY
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.CAFFEINE
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.CHILL_SLEEP_ENVIRONMENT
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.EXCESSIVE_SCREEN_TIME
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.HIGH_STRESS
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.MEDITATION
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.NATURAL_LIGHT
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.PHYSICAL_ACTIVITY
import com.joohnq.sleep_quality.api.property.SleepInfluencesProperties
import org.jetbrains.compose.resources.StringResource

sealed class SleepInfluencesResource(
    override val id: Long,
    val title: StringResource,
) : SleepInfluencesProperties {
    data object NaturalLight :
        SleepInfluencesResource(
            id = NATURAL_LIGHT.id,
            title = Res.string.exposure_to_natural_light
        )

    data object PhysicalActivity :
        SleepInfluencesResource(
            id = PHYSICAL_ACTIVITY.id,
            title = Res.string.physical_activity
        )

    data object ChillSleepEnvironment :
        SleepInfluencesResource(
            id = CHILL_SLEEP_ENVIRONMENT.id,
            title = Res.string.chill_sleep_environment
        )

    data object Meditation :
        SleepInfluencesResource(
            id = MEDITATION.id,
            title = Res.string.meditation
        )

    data object Caffeine :
        SleepInfluencesResource(
            id = CAFFEINE.id,
            title = Res.string.caffeine
        )

    data object ExcessiveScreenTime :
        SleepInfluencesResource(
            id = EXCESSIVE_SCREEN_TIME.id,
            title = Res.string.excessive_screen_time
        )

    data object HighStress :
        SleepInfluencesResource(
            id = HIGH_STRESS.id,
            title = Res.string.high_stress
        )

    data object Anxiety :
        SleepInfluencesResource(
            id = ANXIETY.id,
            title = Res.string.anxiety
        )

    data object AlcoholConsumption :
        SleepInfluencesResource(
            id = ALCOHOL_CONSUMPTION.id,
            title = Res.string.alcohol_consumption
        )

    companion object {
        val allSleepInfluences =
            listOf(
                NaturalLight,
                PhysicalActivity,
                ChillSleepEnvironment,
                Meditation,
                Caffeine,
                ExcessiveScreenTime,
                HighStress,
                Anxiety,
                AlcoholConsumption
            )
    }
}
