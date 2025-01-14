package com.joohnq.mood.domain.use_case

import com.joohnq.core.test.CoreTestConstants
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.fake.StatsRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import kotlin.test.Test

class GetStatsUseCaseTest {
    private var repository: StatsRepositoryFake = StatsRepositoryFake()
    private var useCase: GetStatsUseCase = GetStatsUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling getStatsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke().getOrNull()

            //THEN
            assertThat(res).isEqualTo(
                listOf(
                    StatsRecord(
                        id = 1,
                        mood = Mood.Sad,
                        date = CoreTestConstants.FAKE_DATE
                    ),
                    StatsRecord(
                        id = 2,
                        mood = Mood.Happy,
                        date = CoreTestConstants.FAKE_DATE.plus(1, DateTimeUnit.DAY)
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