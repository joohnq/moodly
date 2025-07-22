package com.joohnq.sleep_quality.api.use_case

import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.fake.SleepQualityRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class AddSleepQualityUseCaseTest {
    private val repository: SleepQualityRepositoryFake = SleepQualityRepositoryFake()
    private val useCase: AddSleepQualityUseCase = AddSleepQualityUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling addSleepQualityUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(item).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid request WHEN calling addSleepQualityUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(item).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to add sleep quality")
        }

    companion object {
        val item = SleepQualityRecord()
    }
}