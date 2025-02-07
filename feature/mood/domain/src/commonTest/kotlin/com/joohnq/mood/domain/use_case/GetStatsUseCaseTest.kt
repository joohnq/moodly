package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.fake.StatsRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import kotlin.test.Test

class GetStatsUseCaseTest {
    private var repository: StatsRepositoryFake = StatsRepositoryFake()
    private var useCase: GetMoodsUseCase = GetMoodsUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling getStatsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke().getOrNull()

            //THEN
            assertThat(res).isEqualTo(
                listOf(
                    MoodRecord(
                        id = 1,
                        mood = Mood.Sad,
                        createdAt = CoreTestConstants.FAKE_DATE
                    ),
                    MoodRecord(
                        id = 2,
                        mood = Mood.Happy,
                        createdAt = CoreTestConstants.FAKE_DATE.plus(1, DateTimeUnit.DAY)
                    )
                )
            )
        }

    @Test
    fun `GIVEN a invalid request WHEN calling getStatsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke().exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to get stats")
        }
}