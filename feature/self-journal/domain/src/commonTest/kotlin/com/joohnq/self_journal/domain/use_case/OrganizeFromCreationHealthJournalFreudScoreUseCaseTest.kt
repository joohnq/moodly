package com.joohnq.self_journal.domain.use_case

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test

class OrganizeFromCreationSelfJournalFreudScoreUseCaseTest {
    companion object {
        val items = listOf(
            SelfJournalRecord(
                id = 1,
                title = "title",
                description = "description",
                mood = Mood.Depressed
            ),
            SelfJournalRecord(
                id = 2,
                title = "title 2",
                description = "description 2",
                mood = Mood.Neutral
            ),
            SelfJournalRecord(
                id = 3,
                title = "title 3",
                description = "description 3",
                mood = Mood.Neutral
            ),
            SelfJournalRecord(
                id = 4,
                title = "title 4",
                description = "description 4",
                mood = Mood.Neutral
            )
        )
    }

    private lateinit var useCase: OrganizeFromCreationSelfJournalFreudScoreUseCase

    @BeforeTest
    fun setUp() {
        useCase = OrganizeFromCreationSelfJournalFreudScoreUseCase()
    }

    @Test
    fun `test OrganizeFromCreationSelfJournalFreudScoreUseCase`() =
        runBlocking {
            val res = useCase.invoke(
                selfJournals = items
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