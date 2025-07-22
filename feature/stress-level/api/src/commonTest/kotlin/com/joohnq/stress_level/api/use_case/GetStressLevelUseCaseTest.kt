package com.joohnq.stress_level.api.use_case

import com.joohnq.stress_level.api.entity.StressLevel
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.entity.Stressor
import com.joohnq.stress_level.api.fake.StressLevelRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class GetStressLevelUseCaseTest {
    private val repository: StressLevelRepositoryFake = StressLevelRepositoryFake()
    private val useCase: GetStressLevelsUseCase = GetStressLevelsUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling getStressLevelsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke().getOrNull()

            //THEN
            assertThat(res).isEqualTo(
                listOf(
                    StressLevelRecord(
                        id = 1,
                        stressLevel = StressLevel.One,
                        stressors = listOf(),
                        createdAt = CoreTestConstants.FAKE_DATE
                    ),
                    StressLevelRecord(
                        id = 2,
                        stressLevel = StressLevel.Two,
                        stressors = listOf(
                            Stressor.Work
                        ),
                        createdAt = CoreTestConstants.FAKE_DATE
                    )
                )
            )
        }

    @Test
    fun `GIVEN a invalid request WHEN calling getStressLevelsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke().exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to get stress levels")
        }
}