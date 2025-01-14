package com.joohnq.mood.domain.use_case

import com.joohnq.core.test.CoreTestConstants
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class OrganizeStatUseCaseTest {
    companion object {
        val items = listOf(
            StatsRecord(
                id = 1,
                mood = Mood.Overjoyed,
                date = CoreTestConstants.FAKE_DATE
            ),
            StatsRecord(
                id = 2,
                mood = Mood.Happy,
                date = CoreTestConstants.FAKE_DATE.plusHour(1)
            ),
            StatsRecord(
                id = 3,
                mood = Mood.Neutral,
                date = CoreTestConstants.FAKE_DATE.plusHour(2)
            )
        )
    }

    private var useCase: OrganizeStatRangeUseCase = OrganizeStatRangeUseCase()

    @Test
    fun `testing OrganizeStatRangeUseCase adding the 0 in start and end`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(items.map { it.mood.healthLevel.toDouble() })

            //THEN
            assertThat(res).isEqualTo(
                listOf(0.0, 100.0, 80.0, 60.0, 0.0)
            )
        }

    @Test
    fun `testing OrganizeStatRangeUseCase without adding 0 in start and end`() =
        runBlocking {
            //WHEN
            val levels = items.map { it.mood.healthLevel.toDouble() }
            val res = useCase.invoke(levels + levels + levels)

            //THEN
            assertThat(res).isEqualTo(
                listOf(100.0, 80.0, 60.0, 100.0, 80.0, 60.0, 100.0, 80.0, 60.0)
            )
        }
}