package com.joohnq.self_journal.data.repository

import com.joohnq.core.test.RobolectricTests
import com.joohnq.core.test.assertDoesNotThrow
import com.joohnq.core.test.assertThatOneOfContains
import com.joohnq.core.test.createTestDriver
import com.joohnq.mood.api.entity.Mood
import com.joohnq.self_journal.impl.data.database.SelfJournalDatabase
import com.joohnq.self_journal.database.SelfJournalDatabaseSql
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository
import com.joohnq.self_journal.impl.data.repository.SelfJournalRepositoryImpl
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class SelfJournalRepositoryTest : RobolectricTests() {
    private lateinit var database: SelfJournalDatabaseSql
    private lateinit var repository: SelfJournalRepository

    @BeforeTest
    fun setUp() {
        val driver = createTestDriver(SelfJournalDatabaseSql.Schema)
        database = SelfJournalDatabase(driver).invoke()
        repository = SelfJournalRepositoryImpl(database)
    }

    private suspend fun fillDumpItems() {
        items.forEach { repository.addSelfJournal(it) }
    }

    @Test
    fun `testing get health journal`() =
        runBlocking {
            //GIVEN
            fillDumpItems()

            //WHEN
            val res = assertDoesNotThrow { repository.getSelfJournals() }
            val items = res.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 1 && it.mood == Mood.Overjoyed && it.title == "title" && it.description == "description"
            }
            items assertThatOneOfContains {
                it.id == 2 && it.mood == Mood.Happy && it.title == "title 2" && it.description == "description 2"
            }
        }

    @Test
    fun `testing add stat`() =
        runBlocking {
            //WHEN
            assertDoesNotThrow { repository.addSelfJournal(items[0]) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getSelfJournals() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 1 && it.mood == Mood.Overjoyed && it.title == "title" && it.description == "description"
            }
        }

    @Test
    fun `testing remove stat`() =
        runBlocking {
            //GIVEN
            fillDumpItems()

            //WHEN
            assertDoesNotThrow { repository.deleteSelfJournal(1) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getSelfJournals() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 2 && it.mood == Mood.Happy && it.title == "title 2" && it.description == "description 2"
            }

            //WHEN
            assertDoesNotThrow { repository.deleteSelfJournal(2) }.getOrThrow()
            val items2 = assertDoesNotThrow { repository.getSelfJournals() }.getOrThrow()

            //THEN
            assertThat(items2).isEmpty()
        }

    companion object {
        val items = listOf(
            SelfJournalRecord(
                id = 1,
                mood = Mood.Overjoyed,
                title = "title",
                description = "description",
                createdAt = CoreTestConstants.FAKE_DATE
            ),
            SelfJournalRecord(
                id = 2,
                mood = Mood.Happy,
                title = "title 2",
                description = "description 2",
                createdAt = CoreTestConstants.FAKE_DATE
            )
        )
    }
}