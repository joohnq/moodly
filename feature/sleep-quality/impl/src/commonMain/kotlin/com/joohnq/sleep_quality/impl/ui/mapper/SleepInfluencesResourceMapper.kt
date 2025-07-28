package com.joohnq.sleep_quality.impl.ui.mapper

import com.joohnq.sleep_quality.api.entity.SleepInfluences
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.AlcoholConsumption
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.Anxiety
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.Caffeine
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.ChillSleepEnvironment
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.ExcessiveScreenTime
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.HighStress
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.Meditation
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.NaturalLight
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.PhysicalActivity

object SleepInfluencesResourceMapper {
    fun allSleepInfluencesResource(): List<SleepInfluencesResource> =
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

    fun SleepInfluences.toResource(): SleepInfluencesResource =
        when (this) {
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

    fun List<SleepInfluences>.toResource(): List<SleepInfluencesResource> = map { it.toResource() }

    fun SleepInfluencesResource.toDomain(): SleepInfluences =
        when (this) {
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
