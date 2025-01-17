package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test

class OrganizeByDateHealthJournalUseCaseTest {
    companion object {
        private val lastYear = LocalDate(2024, 1, 10)
        private val today = LocalDate(2025, 1, 10)
        private val yesterday = LocalDate(2025, 1, 9)
        val items = listOf(
            HealthJournalRecord(
                mood = Mood.Overjoyed,
                createdAt = today
            ),
            HealthJournalRecord(
                mood = Mood.Happy,
                createdAt = today
            ),
            HealthJournalRecord(
                mood = Mood.Neutral,
                createdAt = today
            ),
            HealthJournalRecord(
                mood = Mood.Sad,
                createdAt = yesterday
            ),
            HealthJournalRecord(
                mood = Mood.Sad,
                createdAt = yesterday
            ),
            HealthJournalRecord(
                mood = Mood.Sad,
                createdAt = lastYear
            ),
        )
    }

    private lateinit var useCase: OrganizeByDateHealthJournalUseCase

    @BeforeTest
    fun setUp() {
        useCase = OrganizeByDateHealthJournalUseCase(DatetimeProvider)
    }

    @Test
    fun `test OrganizeByDateHealthJournalUseCase`() =
        runBlocking {
            val res = useCase.invoke(healthJournals = items)
            assertThat(res).isEqualTo(
                mapOf(
                    today to listOf(
                        HealthJournalRecord(
                            mood = Mood.Overjoyed,
                            createdAt = today
                        ),
                        HealthJournalRecord(
                            mood = Mood.Happy,
                            createdAt = today
                        ),
                        HealthJournalRecord(
                            mood = Mood.Neutral,
                            createdAt = today
                        ),
                    ),
                    yesterday to listOf(
                        HealthJournalRecord(
                            mood = Mood.Sad,
                            createdAt = yesterday
                        ),
                        HealthJournalRecord(
                            mood = Mood.Sad,
                            createdAt = yesterday
                        ),
                    ),
                    lastYear to listOf(
                        HealthJournalRecord(
                            mood = Mood.Sad,
                            createdAt = lastYear
                        ),
                    ),
                )
            )
        }
}
