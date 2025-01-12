package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetHealthJournalsInYearUseCaseTest {
    companion object {
        private val lastYear = LocalDateTime(2024, 1, 10, 0, 0, 0)
        private val standard = LocalDateTime(2025, 1, 10, 0, 0, 0)
        private val today = LocalDateTime(2025, 1, 10, 0, 0, 0)
        private val yesterday = LocalDateTime(2025, 1, 9, 0, 0, 0)
        val items = listOf(
            HealthJournalRecord(
                mood = Mood.Overjoyed,
                date = today
            ),
            HealthJournalRecord(
                mood = Mood.Happy,
                date = today
            ),
            HealthJournalRecord(
                mood = Mood.Neutral,
                date = today
            ),
            HealthJournalRecord(
                mood = Mood.Sad,
                date = yesterday
            ),
            HealthJournalRecord(
                mood = Mood.Sad,
                date = yesterday
            ),
            HealthJournalRecord(
                mood = Mood.Sad,
                date = lastYear
            ),
        )
    }

    private lateinit var useCase: GetHealthJournalsInYearUseCase

    @BeforeTest
    fun setUp() {
        useCase = GetHealthJournalsInYearUseCase(DatetimeProvider)
    }

    @Test
    fun `test GetHealthJournalsInYearUseCase with 2 valid items THEN return 2`() =
        runTest {
            val res =
                useCase.invoke(
                    date = standard,
                    healthJournals = items
                )
            assertThat(res).isEqualTo("2/365")
        }

    @Test
    fun `test GetHealthJournalsInYearUseCase with empty list THEN return 0`() =
        runTest {
            val res =
                useCase.invoke(
                    date = standard,
                    healthJournals = emptyList()
                )
            assertThat(res).isEqualTo("0/365")
        }

    @Test
    fun `test GetHealthJournalsInYearUseCase with 2 valid items but different year THEN return 0`() =
        runTest {
            val res =
                useCase.invoke(
                    date = lastYear,
                    healthJournals = items
                )
            assertThat(res).isEqualTo("1/366")
        }
}
