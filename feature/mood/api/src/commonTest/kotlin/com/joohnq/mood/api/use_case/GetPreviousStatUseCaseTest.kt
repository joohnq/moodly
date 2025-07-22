package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.entity.Mood
import com.joohnq.mood.api.entity.MoodRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class GetPreviousStatUseCaseTest {
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

    private var useCase: GetPreviousStatUseCase = GetPreviousStatUseCase()

    @Test
    fun `testing GetPreviousStatUseCase 1`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                record = items[2],
                records = items
            )

            //THEN
            assertThat(res?.id).isEqualTo(2)
        }

    @Test
    fun `testing GetPreviousStatUseCase 2`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                record = items[1],
                records = items
            )

            //THEN
            assertThat(res?.id).isEqualTo(1)
        }

    @Test
    fun `testing GetPreviousStatUseCase 3`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                record = items[0],
                records = items
            )

            //THEN
            assertThat(res?.id).isEqualTo(null)
        }
}