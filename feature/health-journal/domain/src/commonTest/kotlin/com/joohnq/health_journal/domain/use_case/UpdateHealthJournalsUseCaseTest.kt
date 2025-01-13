package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.fake.HealthJournalRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class UpdateHealthJournalsUseCaseTest {
    companion object {
        val healthJournalRecord = HealthJournalRecord(id = 1)
        val invalidHealthJournalRecord = HealthJournalRecord(id = 10)
    }

    private lateinit var useCase: UpdateHealthJournalsUseCase
    private lateinit var repository: HealthJournalRepositoryFake

    @BeforeTest
    fun setUp() {
        repository = HealthJournalRepositoryFake()
        useCase = UpdateHealthJournalsUseCase(repository)
    }

    @Test
    fun `GIVEN a valid request WHEN calling updateHealthJournalsUseCase THEN should return true`() =
        runTest {
            //WHEN
            val res = useCase.invoke(healthJournalRecord).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid id WHEN calling updateHealthJournalsUseCase THEN should return exception in failure`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(invalidHealthJournalRecord).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Health journal with id not founded")
        }

    @Test
    fun `GIVEN a invalid request WHEN calling updateHealthJournalsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(healthJournalRecord).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to update health journal")
        }
}
