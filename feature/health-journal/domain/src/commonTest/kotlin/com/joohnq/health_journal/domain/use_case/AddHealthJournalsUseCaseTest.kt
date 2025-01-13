package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.fake.HealthJournalRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class AddHealthJournalsUseCaseTest {
    companion object {
        val item = HealthJournalRecord()
    }

    private lateinit var useCase: AddHealthJournalsUseCase
    private lateinit var repository: HealthJournalRepositoryFake

    @BeforeTest
    fun setUp() {
        repository = HealthJournalRepositoryFake()
        useCase = AddHealthJournalsUseCase(repository)
    }

    @Test
    fun `GIVEN a valid request WHEN calling addHealthJournalsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(item).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid request WHEN calling addHealthJournalsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(item).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to add health journal")
        }
}
