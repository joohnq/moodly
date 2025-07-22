package com.joohnq.self_journal.api.use_case

import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.mood.api.entity.Mood
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test

class OrganizeByDateSelfJournalUseCaseTest {
    companion object {
        private val lastYear = LocalDate(2024, 1, 10)
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

    private lateinit var useCase: OrganizeByDateSelfJournalUseCase

    @BeforeTest
    fun setUp() {
        useCase = OrganizeByDateSelfJournalUseCase(DatetimeProvider)
    }

    @Test
    fun `test OrganizeByDateSelfJournalUseCase`() =
        runBlocking {
            val res = useCase.invoke(selfJournals = items)
            assertThat(res).isEqualTo(
                mapOf(
                    today to listOf(
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
                    ),
                    yesterday to listOf(
                        SelfJournalRecord(
                            mood = Mood.Sad,
                            createdAt = yesterday
                        ),
                        SelfJournalRecord(
                            mood = Mood.Sad,
                            createdAt = yesterday
                        ),
                    ),
                    lastYear to listOf(
                        SelfJournalRecord(
                            mood = Mood.Sad,
                            createdAt = lastYear
                        ),
                    ),
                )
            )
        }
}
