package com.joohnq.self_journal.api.use_case

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.fake.SelfJournalRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetSelfJournalsUseCaseTest {
    private lateinit var useCase: GetSelfJournalsUseCase
    private lateinit var repository: SelfJournalRepositoryFake

    @BeforeTest
    fun setUp() {
        repository = SelfJournalRepositoryFake()
        useCase = GetSelfJournalsUseCase(repository)
    }

    @Test
    fun `GIVEN a valid request WHEN calling getSelfJournalsUseCase THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke().getOrNull()

            //THEN
            assertThat(res).isEqualTo(
                listOf(
                    SelfJournalRecord(
                        id = 1,
                        title = "title",
                        description = "description",
                        createdAt = CoreTestConstants.FAKE_DATE,
                        mood = Mood.Depressed
                    ),
                    SelfJournalRecord(
                        id = 2,
                        title = "title 2",
                        description = "description 2",
                        createdAt = CoreTestConstants.FAKE_DATE,
                        mood = Mood.Neutral
                    )
                )
            )
        }

    @Test
    fun `GIVEN a invalid request WHEN calling getSelfJournalsUseCase THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke().exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to get health journals")
        }
}
