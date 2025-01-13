package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.fake.StatsRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class DeleteStatsUseCaseTest {
    private var repository: StatsRepositoryFake = StatsRepositoryFake()
    private var useCase: DeleteStatsUseCase = DeleteStatsUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling deleteStatsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(1).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid id WHEN calling deleteStatsUseCase THEN should return exception in failure`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(10).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Stat with id not founded")
        }

    @Test
    fun `GIVEN a invalid request WHEN calling deleteStatsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(1).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to delete stat")
        }
}