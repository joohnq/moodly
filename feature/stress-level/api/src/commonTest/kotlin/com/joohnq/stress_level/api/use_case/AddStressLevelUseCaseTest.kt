package com.joohnq.stress_level.api.use_case

import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.fake.StressLevelRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class AddStressLevelUseCaseTest {
    private val repository: StressLevelRepositoryFake = StressLevelRepositoryFake()
    private val useCase: AddStressLevelUseCase = AddStressLevelUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling addStressLevelUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(item).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid request WHEN calling addStressLevelUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(item).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to add stress level")
        }

    companion object {
        val item = StressLevelRecord()
    }
}