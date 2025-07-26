package com.joohnq.stress_level.impl.ui.resource

import com.joohnq.api.getNow
import com.joohnq.stress_level.impl.ui.resource.StressorResource.Companion.allStressors
import kotlinx.datetime.LocalDateTime

data class StressLevelRecordResource(
    val id: Int = -1,
    val stressLevel: StressLevelResource = StressLevelResource.One,
    val stressors: List<StressorResource> = emptyList(),
    val createdAt: LocalDateTime = getNow(),
) {
    companion object {
        val stressLevelRecordResourceOnePreview =
            StressLevelRecordResource(
                id = StressLevelResource.One.id,
                stressLevel = StressLevelResource.One
            )

        val stressLevelRecordResourceTwoPreview =
            StressLevelRecordResource(
                id = StressLevelResource.Two.id,
                stressLevel = StressLevelResource.Two
            )

        val stressLevelRecordResourceThreePreview =
            StressLevelRecordResource(
                id = StressLevelResource.Three.id,
                stressLevel = StressLevelResource.Three
            )

        val stressLevelRecordResourceFourPreview =
            StressLevelRecordResource(
                id = StressLevelResource.Four.id,
                stressLevel = StressLevelResource.Four
            )

        val stressLevelRecordResourceFivePreview =
            StressLevelRecordResource(
                id = StressLevelResource.Five.id,
                stressLevel = StressLevelResource.Five
            )

        val stressLevelRecordResourceWithStressorsPreview =
            StressLevelRecordResource(
                id = 6,
                stressLevel = StressLevelResource.Five,
                stressors = allStressors
            )

        val allStressLevelRecordResourcePreview =
            listOf(
                stressLevelRecordResourceOnePreview,
                stressLevelRecordResourceTwoPreview,
                stressLevelRecordResourceThreePreview,
                stressLevelRecordResourceFourPreview,
                stressLevelRecordResourceFivePreview,
                stressLevelRecordResourceWithStressorsPreview
            )
    }
}
