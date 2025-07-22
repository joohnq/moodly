package com.joohnq.self_journal.api.use_case

import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.mood.api.entity.Mood
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetSelfJournalsInYearUseCaseTest {
    companion object {
        private val lastYear = LocalDate(2024, 1, 10)
        private val standard = LocalDate(2025, 1, 10)
        private val today = LocalDate(2025, 1, 10)
        private val yesterday = LocalDate(2025, 1, 9)
        val items = listOf(
            SelfJournalRecord(
                mood = Mood.Overjoyed,
                createdAt = today
            ),
            SelfJournalRecord(
                mood = Mood.Happy,
                createdAt = today
            ),
            SelfJournalRecord(
                mood = Mood.Neutral,
                createdAt = today
            ),
            SelfJournalRecord(
                mood = Mood.Sad,
                createdAt = yesterday
            ),
            SelfJournalRecord(
                mood = Mood.Sad,
                createdAt = yesterday
            ),
            SelfJournalRecord(
                mood = Mood.Sad,
                createdAt = lastYear
            ),
        )
    }

    private lateinit var useCase: GetSelfJournalsInYearUseCase

    @BeforeTest
    fun setUp() {
        useCase = GetSelfJournalsInYearUseCase(DatetimeProvider)
    }

    @Test
    fun `test GetSelfJournalsInYearUseCase with 2 valid items THEN return 2`() =
        runBlocking {
            val res =
                useCase.invoke(
                    date = standard,
                    selfJournals = items
                )
            assertThat(res).isEqualTo("2/365")
        }

    @Test
    fun `test GetSelfJournalsInYearUseCase with empty list THEN return 0`() =
        runBlocking {
            val res =
                useCase.invoke(
                    date = standard,
                    selfJournals = emptyList()
                )
            assertThat(res).isEqualTo("0/365")
        }

    @Test
    fun `test GetSelfJournalsInYearUseCase with 2 valid items but different year THEN return 0`() =
        runBlocking {
            val res =
                useCase.invoke(
                    date = lastYear,
                    selfJournals = items
                )
            assertThat(res).isEqualTo("1/366")
        }
}
