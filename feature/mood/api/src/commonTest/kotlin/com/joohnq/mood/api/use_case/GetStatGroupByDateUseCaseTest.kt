package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.entity.Mood
import com.joohnq.mood.api.entity.MoodRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.test.Test

class GetStatGroupByDateUseCaseTest {
    private var useCase: GetStatGroupByDateUseCase = GetStatGroupByDateUseCase()

    @Test
    fun `GIVEN a valid request WHEN calling getStatsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(items)

            //THEN
            assertThat(res).isEqualTo(
                mapOf(
                    LocalDate(2025, 1, 1) to listOf(
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
                        ),
                    ),
                    LocalDate(2025, 1, 2) to listOf(
                        MoodRecord(
                            id = 3,
                            mood = Mood.Neutral,
                            createdAt = CoreTestConstants.FAKE_DATE.plus(1, DateTimeUnit.DAY)
                        )
                    )
                ).toList()
            )
        }

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
            ),
            MoodRecord(
                id = 3,
                mood = Mood.Neutral,
                createdAt = CoreTestConstants.FAKE_DATE.plusDays(1).plusHour(2)
            )
        )
    }
}