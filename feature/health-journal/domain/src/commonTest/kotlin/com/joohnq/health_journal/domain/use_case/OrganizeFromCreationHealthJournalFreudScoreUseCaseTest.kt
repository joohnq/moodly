package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test

class OrganizeFromCreationHealthJournalFreudScoreUseCaseTest {
    companion object {
        val items = listOf(
            HealthJournalRecord(
                id = 1,
                title = "title",
                description = "description",
                date = LocalDateTime(2025, 1, 1, 1, 0, 0),
                mood = Mood.Depressed
            ),
            HealthJournalRecord(
                id = 2,
                title = "title 2",
                description = "description 2",
                date = LocalDateTime(2025, 1, 1, 2, 0, 0),
                mood = Mood.Neutral
            ),
            HealthJournalRecord(
                id = 3,
                title = "title 3",
                description = "description 3",
                date = LocalDateTime(2025, 1, 2, 3, 0, 0),
                mood = Mood.Neutral
            ),
            HealthJournalRecord(
                id = 4,
                title = "title 4",
                description = "description 4",
                date = LocalDateTime(2025, 1, 2, 1, 10, 0),
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
        runTest {
            val res = useCase.invoke(
                creationDate = LocalDateTime(2025, 1, 1, 0, 0, 0),
                currentDate = LocalDateTime(2025, 1, 3, 0, 0, 0),
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