package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
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
                        ),
                    ),
                    LocalDate(2025, 1, 2) to listOf(
                        StatsRecord(
                            id = 3,
                            mood = Mood.Neutral,
                            date = LocalDateTime(2025, 1, 2, 3, 0, 0)
                        )
                    )
                ).toList()
            )
        }

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
            ),
            StatsRecord(
                id = 3,
                mood = Mood.Neutral,
                date = LocalDateTime(2025, 1, 2, 3, 0, 0)
            )
        )
    }
}