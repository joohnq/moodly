package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test

class OrganizeStatUseCaseTest {
    companion object {
        val items = listOf(
            StatsRecord(
                id = 1,
                mood = Mood.Overjoyed,
                date = LocalDateTime(2025, 1, 1, 1, 0, 0)
            ),
            StatsRecord(
                id = 2,
                mood = Mood.Happy,
                date = LocalDateTime(2025, 1, 1, 2, 0, 0)
            ),
            StatsRecord(
                id = 3,
                mood = Mood.Neutral,
                date = LocalDateTime(2025, 1, 1, 3, 0, 0)
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