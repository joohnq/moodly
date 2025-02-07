package com.joohnq.self_journal.domain.use_case

import com.joohnq.self_journal.domain.fake.SelfJournalRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class DeleteSelfJournalsUseCaseTest {
    companion object {
        const val ID = 1
    }

    private lateinit var useCase: DeleteSelfJournalsUseCase
    private lateinit var repository: SelfJournalRepositoryFake

    @BeforeTest
    fun setUp() {
        repository = SelfJournalRepositoryFake()
        useCase = DeleteSelfJournalsUseCase(repository)
    }

    @Test
    fun `GIVEN a valid request WHEN calling deleteSelfJournalsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(ID).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid id WHEN calling deleteSelfJournalsUseCase THEN should return exception in failure`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(10).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Health journal with id not founded")
        }

    @Test
    fun `GIVEN a invalid request WHEN calling deleteSelfJournalsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(ID).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to delete health journal")
        }
}
