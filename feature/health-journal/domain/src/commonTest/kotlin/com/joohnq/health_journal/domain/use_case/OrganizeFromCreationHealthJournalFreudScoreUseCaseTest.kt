package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.test.BeforeTest
import kotlin.test.Test

class OrganizeFromCreationHealthJournalFreudScoreUseCaseTest {
    companion object {
        val items = listOf(
            HealthJournalRecord(
                id = 1,
                title = "title",
                description = "description",
                createdAt = CoreTestConstants.FAKE_DATE,
                mood = Mood.Depressed
            ),
            HealthJournalRecord(
                id = 2,
                title = "title 2",
                description = "description 2",
                createdAt = CoreTestConstants.FAKE_DATE,
                mood = Mood.Neutral
            ),
            HealthJournalRecord(
                id = 3,
                title = "title 3",
                description = "description 3",
                createdAt = CoreTestConstants.FAKE_DATE,
                mood = Mood.Neutral
            ),
            HealthJournalRecord(
                id = 4,
                title = "title 4",
                description = "description 4",
                createdAt = CoreTestConstants.FAKE_DATE,
                mood = Mood.Neutral
            )
        )
    }

    private lateinit var useCase: OrganizeFromCreationHealthJournalFreudScoreUseCase

    @BeforeTest
    fun setUp() {
        useCase = OrganizeFromCreationHealthJournalFreudScoreUseCase(DatetimeProvider)
    }

    @Test
    fun `test OrganizeFromCreationHealthJournalFreudScoreUseCase`() =
        runBlocking {
            val res = useCase.invoke(
                creationDate = CoreTestConstants.FAKE_DATE,
                currentDate = CoreTestConstants.FAKE_DATE.plus(2, DateTimeUnit.DAY),
                healthJournals = items
            )
            assertThat(res).isEqualTo(
                mapOf(
                    LocalDate(2025, 1, 1) to listOf(
                        items[0],
                        items[1],
                    ),
                    LocalDate(2025, 1, 2) to listOf(
                        items[2],
                        items[3],
                    ),
                    LocalDate(2025, 1, 3) to null,
                ).toList()
            )
        }
}