package com.joohnq.mood.data.repository

import com.joohnq.mood.data.database.MoodDatabase
import com.joohnq.mood.database.MoodDatabaseSql
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.repository.MoodRepository
import com.joohnq.test.assertDoesNotThrow
import com.joohnq.test.assertThatOneOfContains
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class MoodRepositoryTest {
    private lateinit var database: MoodDatabaseSql
    private lateinit var repository: MoodRepository

    @BeforeTest
    fun setUp() {
        database = MoodDatabase(driver).invoke()
        repository = MoodRepositoryImpl(database)
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)

        val database = MoodDatabase(driver)

        MoodDatabase.Schema.create(driver)
    }

    private suspend fun fillDumpItems() {
        items.forEach { repository.addMoodRecord(it) }
    }

    @Test
    fun `testing get stats`() =
        runBlocking {
            //GIVEN
            fillDumpItems()

            //WHEN
            val res = assertDoesNotThrow { repository.getMoodRecords() }
            val items = res.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 1 && it.mood == Mood.Overjoyed && it.description == "description"
            }
            items assertThatOneOfContains {
                it.id == 2 && it.mood == Mood.Happy && it.description == "description 2"
            }
        }

    @Test
    fun `testing add stat`() =
        runBlocking {
            //GIVEN
            val item = MoodRecord(
                id = 3,
                mood = Mood.Depressed,
                description = "description 3",
                createdAt = CoreTestConstants.FAKE_DATE
            )

            //WHEN
            assertDoesNotThrow { repository.addMoodRecord(item) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getMoodRecords() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 1 && it.mood == Mood.Depressed && it.description == "description 3"
            }
        }

    @Test
    fun `testing remove stat`() =
        runBlocking {
            //GIVEN
            fillDumpItems()

            //WHEN
            assertDoesNotThrow { repository.deleteMoodRecord(1) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getMoodRecords() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 2 && it.mood == Mood.Happy && it.description == "description 2"
            }

            //WHEN
            assertDoesNotThrow { repository.deleteMoodRecord(2) }.getOrThrow()
            val items2 = assertDoesNotThrow { repository.getMoodRecords() }.getOrThrow()

            //THEN
            assertThat(items2).isEmpty()
        }

    companion object {
        val items = listOf(
            MoodRecord(
                id = 1,
                mood = Mood.Overjoyed,
                description = "description",
                createdAt = CoreTestConstants.FAKE_DATE
            ),
            MoodRecord(
                id = 2,
                mood = Mood.Happy,
                description = "description 2",
                createdAt = CoreTestConstants.FAKE_DATE
            )
        )
    }
}