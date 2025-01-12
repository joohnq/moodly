package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.fake.HealthJournalRepositoryFake
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetHealthJournalsUseCaseTest {
    private lateinit var useCase: GetHealthJournalsUseCase
    private lateinit var repository: HealthJournalRepositoryFake

    @BeforeTest
    fun setUp() {
        repository = HealthJournalRepositoryFake()
        useCase = GetHealthJournalsUseCase(repository)
    }

    @Test
    fun `GIVEN a valid request WHEN calling getHealthJournalsUseCase THEN should return true`() =
        runTest {
            //WHEN
            val res = useCase.invoke().getOrNull()

            //THEN
            assertThat(res).isEqualTo(
                listOf(
                    HealthJournalRecord(
                        id = 1,
                        title = "title",
                        description = "description",
                        date = LocalDateTime(2022, 1, 1, 0, 0, 0),
                        mood = Mood.Depressed
                    ),
                    HealthJournalRecord(
                        id = 2,
                        title = "title 2",
                        description = "description 2",
                        date = LocalDateTime(2024, 1, 1, 0, 0, 0),
                        mood = Mood.Neutral
                    )
                )
            )
        }

    @Test
    fun `GIVEN a invalid request WHEN calling getHealthJournalsUseCase THEN should return exception in failure`() =
        runTest {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke().exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to get health journals")
        }
}
