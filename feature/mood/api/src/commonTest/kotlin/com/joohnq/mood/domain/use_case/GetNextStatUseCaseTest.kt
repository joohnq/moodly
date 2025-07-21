package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.MoodRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class GetNextStatUseCaseTest {
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

    private var useCase: GetNextStatUseCase = GetNextStatUseCase()

    @Test
    fun `testing GetNextStatUseCase 1`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                record = items[0],
                moodRecords = items
            )

            //THEN
            assertThat(res?.id).isEqualTo(2)
        }

    @Test
    fun `testing GetNextStatUseCase 2`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                record = items[1],
                moodRecords = items
            )

            //THEN
            assertThat(res?.id).isEqualTo(3)
        }

    @Test
    fun `testing GetNextStatUseCase 3`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                record = items[2],
                moodRecords = items
            )

            //THEN
            assertThat(res?.id).isEqualTo(null)
        }
}