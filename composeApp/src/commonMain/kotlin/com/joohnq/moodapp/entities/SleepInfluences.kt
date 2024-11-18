package com.joohnq.moodapp.entities

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.alcohol_consumption
import moodapp.composeapp.generated.resources.anxiety
import moodapp.composeapp.generated.resources.caffeine
import moodapp.composeapp.generated.resources.chill_sleep_environment
import moodapp.composeapp.generated.resources.excessive_screen_time
import moodapp.composeapp.generated.resources.exposure_to_natural_light
import moodapp.composeapp.generated.resources.high_stress
import moodapp.composeapp.generated.resources.meditation
import moodapp.composeapp.generated.resources.physical_activity
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class SleepInfluences(val id: Int, @Contextual val title: StringResource) {
    data object NaturalLight :
        SleepInfluences(id = NATURAL_LIGHT, title = Res.string.exposure_to_natural_light)

    data object PhysicalActivity :
        SleepInfluences(id = PHYSICAL_ACTIVITY, title = Res.string.physical_activity)

    data object ChillSleepEnvironment :
        SleepInfluences(id = CHILL_SLEEP_ENVIRONMENT, title = Res.string.chill_sleep_environment)

    data object Meditation : SleepInfluences(id = MEDITATION, title = Res.string.meditation)
    data object Caffeine : SleepInfluences(id = CAFFEINE, title = Res.string.caffeine)
    data object ExcessiveScreenTime :
        SleepInfluences(id = EXCESSIVE_SCREEN_TIME, title = Res.string.excessive_screen_time)

    data object HighStress : SleepInfluences(id = HIGH_STRESS, title = Res.string.high_stress)
    data object Anxiety : SleepInfluences(id = ANXIETY, title = Res.string.anxiety)
    data object AlcoholConsumption :
        SleepInfluences(id = ALCOHOL_CONSUMPTION, title = Res.string.alcohol_consumption)

    companion object {
        private const val NATURAL_LIGHT = 0
        private const val PHYSICAL_ACTIVITY = 1
        private const val CHILL_SLEEP_ENVIRONMENT = 2
        private const val MEDITATION = 3
        private const val CAFFEINE = 4
        private const val EXCESSIVE_SCREEN_TIME = 5
        private const val HIGH_STRESS = 6
        private const val ANXIETY = 7
        private const val ALCOHOL_CONSUMPTION = 8

        fun getAll(): List<SleepInfluences> = listOf(
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

        fun toValue(src: Int): SleepInfluences = when (src) {
            NATURAL_LIGHT -> NaturalLight
            PHYSICAL_ACTIVITY -> PhysicalActivity
            CHILL_SLEEP_ENVIRONMENT -> ChillSleepEnvironment
            MEDITATION -> Meditation
            CAFFEINE -> Caffeine
            EXCESSIVE_SCREEN_TIME -> ExcessiveScreenTime
            HIGH_STRESS -> HighStress
            ANXIETY -> Anxiety
            ALCOHOL_CONSUMPTION -> AlcoholConsumption
            else -> throw IllegalArgumentException("Unknown sleep influence option: $src")
        }

        fun fromValue(sleepInfluences: SleepInfluences): Int = sleepInfluences.id
    }
}
