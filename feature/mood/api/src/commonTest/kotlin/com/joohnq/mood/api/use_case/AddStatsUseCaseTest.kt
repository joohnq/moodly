package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.fake.StatsRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class AddStatsUseCaseTest {
    companion object {
        val item = MoodRecord()
    }

    private var repository: StatsRepositoryFake = StatsRepositoryFake()
    private var useCase: AddMoodUseCase = AddMoodUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling addStatsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(item).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid request WHEN calling addStatsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(item).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to add stat")
        }
}