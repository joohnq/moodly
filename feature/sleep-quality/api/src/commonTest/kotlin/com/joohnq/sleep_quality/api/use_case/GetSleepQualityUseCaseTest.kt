package com.joohnq.sleep_quality.api.use_case

import com.joohnq.sleep_quality.api.entity.SleepQuality
import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.fake.SleepQualityRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class GetSleepQualityUseCaseTest {
    private val repository: SleepQualityRepositoryFake = SleepQualityRepositoryFake()
    private val useCase: GetSleepQualitiesUseCase = GetSleepQualitiesUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling getSleepQualitiesUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke().getOrNull()

            //THEN
            assertThat(res).isEqualTo(
                listOf(
                    SleepQualityRecord(
                        id = 1,
                        sleepQuality = SleepQuality.Fair,
                        startSleeping = "00:00",
                        endSleeping = "06:00",
                        sleepInfluences = listOf(),
                        createdAt = CoreTestConstants.FAKE_DATE
                    )
                )
            )
        }

    @Test
    fun `GIVEN a invalid request WHEN calling getSleepQualitiesUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke().exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to get sleep qualities")
        }
}