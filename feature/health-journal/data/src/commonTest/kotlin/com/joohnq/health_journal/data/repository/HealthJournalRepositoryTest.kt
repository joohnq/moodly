package com.joohnq.health_journal.data.repository

import com.joohnq.core.test.RobolectricTests
import com.joohnq.core.test.assertDoesNotThrow
import com.joohnq.core.test.assertThatContains
import com.joohnq.core.test.createTestDriver
import com.joohnq.health_journal.data.database.HealthJournalDatabase
import com.joohnq.health_journal.database.HealthJournalDatabaseSql
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test

class HealthJournalRepositoryTest : RobolectricTests() {
    private lateinit var database: HealthJournalDatabaseSql
    private lateinit var repository: HealthJournalRepository

    @BeforeTest
    fun setUp() {
        val driver = createTestDriver(HealthJournalDatabaseSql.Schema)
        database = HealthJournalDatabase(driver).invoke()
        repository = HealthJournalRepositoryImpl(database)
    }

    private suspend fun fillDumpItems() {
        items.forEach { repository.addHealthJournal(it) }
    }

    @Test
    fun `testing get health journal`() =
        runBlocking {
            //GIVEN
            fillDumpItems()

            //WHEN
            val res = assertDoesNotThrow { repository.getHealthJournals() }
            val items = res.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatContains {
                it.id == 1 && it.mood == Mood.Overjoyed && it.title == "title" && it.description == "description"
            }
            items assertThatContains {
                it.id == 2 && it.mood == Mood.Happy && it.title == "title 2" && it.description == "description 2"
            }
        }

    @Test
    fun `testing add stat`() =
        runBlocking {
            //WHEN
            assertDoesNotThrow { repository.addHealthJournal(items[0]) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getHealthJournals() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatContains {
                it.id == 1 && it.mood == Mood.Overjoyed && it.title == "title" && it.description == "description"
            }
        }

    @Test
    fun `testing remove stat`() =
        runBlocking {
            //GIVEN
            fillDumpItems()

            //WHEN
            assertDoesNotThrow { repository.deleteHealthJournal(1) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getHealthJournals() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatContains {
                it.id == 2 && it.mood == Mood.Happy && it.title == "title 2" && it.description == "description 2"
            }

            //WHEN
            assertDoesNotThrow { repository.deleteHealthJournal(2) }.getOrThrow()
            val items2 = assertDoesNotThrow { repository.getHealthJournals() }.getOrThrow()

            //THEN
            assertThat(items2).isEmpty()
        }

    companion object {
        val items = listOf(
            HealthJournalRecord(
                id = 1,
                mood = Mood.Overjoyed,
                title = "title",
                description = "description",
                date = LocalDateTime(2025, 1, 1, 0, 0, 0)
            ),
            HealthJournalRecord(
                id = 2,
                mood = Mood.Happy,
                title = "title 2",
                description = "description 2",
                date = LocalDateTime(2025, 1, 1, 0, 0, 0)
            )
        )
    }
}