package com.joohnq.sleep_quality.ui

import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.alcohol_consumption
import com.joohnq.shared.ui.anxiety
import com.joohnq.shared.ui.caffeine
import com.joohnq.shared.ui.chill_sleep_environment
import com.joohnq.shared.ui.excessive_screen_time
import com.joohnq.shared.ui.exposure_to_natural_light
import com.joohnq.shared.ui.high_stress
import com.joohnq.shared.ui.meditation
import com.joohnq.shared.ui.physical_activity
import com.joohnq.sleep_quality.domain.SleepInfluencesProperties
import com.joohnq.sleep_quality.domain.entity.SleepInfluences
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.ALCOHOL_CONSUMPTION
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.ANXIETY
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.CAFFEINE
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.CHILL_SLEEP_ENVIRONMENT
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.EXCESSIVE_SCREEN_TIME
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.HIGH_STRESS
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.MEDITATION
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.NATURAL_LIGHT
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.PHYSICAL_ACTIVITY
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class SleepInfluencesResource(
    override val id: Int,
    @Contextual val title: StringResource,
) : SleepInfluencesProperties {
    data object NaturalLight :
        SleepInfluencesResource(id = NATURAL_LIGHT.id, title = Res.string.exposure_to_natural_light)

    data object PhysicalActivity :
        SleepInfluencesResource(id = PHYSICAL_ACTIVITY.id, title = Res.string.physical_activity)

    data object ChillSleepEnvironment :
        SleepInfluencesResource(
            id = CHILL_SLEEP_ENVIRONMENT.id,
            title = Res.string.chill_sleep_environment
        )

    data object Meditation :
        SleepInfluencesResource(id = MEDITATION.id, title = Res.string.meditation)

    data object Caffeine : SleepInfluencesResource(id = CAFFEINE.id, title = Res.string.caffeine)
    data object ExcessiveScreenTime :
        SleepInfluencesResource(
            id = EXCESSIVE_SCREEN_TIME.id,
            title = Res.string.excessive_screen_time
        )

    data object HighStress :
        SleepInfluencesResource(id = HIGH_STRESS.id, title = Res.string.high_stress)

    data object Anxiety : SleepInfluencesResource(id = ANXIETY.id, title = Res.string.anxiety)
    data object AlcoholConsumption :
        SleepInfluencesResource(id = ALCOHOL_CONSUMPTION.id, title = Res.string.alcohol_consumption)

    companion object {
        fun getAll(): List<SleepInfluencesResource> = listOf(
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

        fun SleepInfluences.toResource(): SleepInfluencesResource = when (this) {
            SleepInfluences.NaturalLight -> NaturalLight
            SleepInfluences.PhysicalActivity -> PhysicalActivity
            SleepInfluences.ChillSleepEnvironment -> ChillSleepEnvironment
            SleepInfluences.Meditation -> Meditation
            SleepInfluences.Caffeine -> Caffeine
            SleepInfluences.ExcessiveScreenTime -> ExcessiveScreenTime
            SleepInfluences.HighStress -> HighStress
            SleepInfluences.Anxiety -> Anxiety
            SleepInfluences.AlcoholConsumption -> AlcoholConsumption
        }

        fun SleepInfluencesResource.toDomain(): SleepInfluences = when (this) {
            NaturalLight -> SleepInfluences.NaturalLight
            PhysicalActivity -> SleepInfluences.PhysicalActivity
            ChillSleepEnvironment -> SleepInfluences.ChillSleepEnvironment
            Meditation -> SleepInfluences.Meditation
            Caffeine -> SleepInfluences.Caffeine
            ExcessiveScreenTime -> SleepInfluences.ExcessiveScreenTime
            HighStress -> SleepInfluences.HighStress
            Anxiety -> SleepInfluences.Anxiety
            AlcoholConsumption -> SleepInfluences.AlcoholConsumption
        }

        fun List<SleepInfluencesResource>.toDomain(): List<SleepInfluences> = map { it.toDomain() }
    }
}