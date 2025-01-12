package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.fake.HealthJournalRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class DeleteHealthJournalsUseCaseTest {
    companion object {
        const val ID = 1
    }

    private lateinit var useCase: DeleteHealthJournalsUseCase
    private lateinit var repository: HealthJournalRepositoryFake

    @BeforeTest
    fun setUp() {
        repository = HealthJournalRepositoryFake()
        useCase = DeleteHealthJournalsUseCase(repository)
    }

    @Test
    fun `GIVEN a valid request WHEN calling deleteHealthJournalsUseCase THEN should return true`() =
        runTest {
            //WHEN
            val res = useCase.invoke(ID).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)
        }

    @Test
    fun `GIVEN a invalid request WHEN calling deleteHealthJournalsUseCase THEN should return exception in failure`() =
        runTest {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(ID).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to delete health journal")
        }
}
