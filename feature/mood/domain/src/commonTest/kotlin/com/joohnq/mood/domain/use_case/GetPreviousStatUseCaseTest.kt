package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test

class GetPreviousStatUseCaseTest {
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

    private var useCase: GetPreviousStatUseCase = GetPreviousStatUseCase()

    @Test
    fun `testing GetPreviousStatUseCase 1`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                statsRecord = items[2],
                statsRecords = items
            )

            //THEN
            assertThat(res?.id).isEqualTo(2)
        }

    @Test
    fun `testing GetPreviousStatUseCase 2`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                statsRecord = items[1],
                statsRecords = items
            )

            //THEN
            assertThat(res?.id).isEqualTo(1)
        }

    @Test
    fun `testing GetPreviousStatUseCase 3`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                statsRecord = items[0],
                statsRecords = items
            )

            //THEN
            assertThat(res?.id).isEqualTo(null)
        }
}