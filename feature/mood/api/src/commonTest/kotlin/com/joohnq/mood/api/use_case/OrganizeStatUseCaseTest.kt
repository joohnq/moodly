package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.entity.Mood
import com.joohnq.mood.api.entity.MoodRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class OrganizeStatUseCaseTest {
    companion object {
        val items = listOf(
            MoodRecord(
                id = 1,
                mood = Mood.Overjoyed,
                createdAt = CoreTestConstants.FAKE_DATE
            ),
            MoodRecord(
                id = 2,
                mood = Mood.Happy,
                createdAt = CoreTestConstants.FAKE_DATE.plusHour(1)
            ),
            MoodRecord(
                id = 3,
                mood = Mood.Neutral,
                createdAt = CoreTestConstants.FAKE_DATE.plusHour(2)
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