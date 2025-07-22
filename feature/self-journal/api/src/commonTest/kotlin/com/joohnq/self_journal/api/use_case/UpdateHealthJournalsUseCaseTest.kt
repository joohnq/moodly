package com.joohnq.self_journal.api.use_case

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.fake.SelfJournalRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class UpdateSelfJournalsUseCaseTest {
    companion object {
        val selfJournalRecord = SelfJournalRecord(id = 1)
        val invalidSelfJournalRecord = SelfJournalRecord(id = 10)
    }

    private lateinit var useCase: UpdateSelfJournalsUseCase
    private lateinit var repository: SelfJournalRepositoryFake

    @BeforeTest
    fun setUp() {
        repository = SelfJournalRepositoryFake()
        useCase = UpdateSelfJournalsUseCase(repository)
    }

    @Test
    fun `GIVEN a valid request WHEN calling updateSelfJournalsUseCase THEN should return true`() =
        runTest {
            //WHEN
            val res = useCase.invoke(selfJournalRecord).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid id WHEN calling updateSelfJournalsUseCase THEN should return exception in failure`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(invalidSelfJournalRecord).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Health journal with id not founded")
        }

    @Test
    fun `GIVEN a invalid request WHEN calling updateSelfJournalsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(selfJournalRecord).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to update health journal")
        }
}
