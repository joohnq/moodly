package com.joohnq.self_journal.domain.use_case

import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.fake.SelfJournalRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class AddSelfJournalsUseCaseTest {
    companion object {
        val item = SelfJournalRecord()
    }

    private lateinit var useCase: AddSelfJournalsUseCase
    private lateinit var repository: SelfJournalRepositoryFake

    @BeforeTest
    fun setUp() {
        repository = SelfJournalRepositoryFake()
        useCase = AddSelfJournalsUseCase(repository)
    }

    @Test
    fun `GIVEN a valid request WHEN calling addSelfJournalsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(item).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid request WHEN calling addSelfJournalsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(item).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to add health journal")
        }
}
