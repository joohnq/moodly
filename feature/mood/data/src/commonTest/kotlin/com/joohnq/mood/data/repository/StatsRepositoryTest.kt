package com.joohnq.mood.data.repository

import com.joohnq.core.test.RobolectricTests
import com.joohnq.core.test.assertDoesNotThrow
import com.joohnq.core.test.assertThatOneOfContains
import com.joohnq.core.test.createTestDriver
import com.joohnq.mood.data.database.StatsDatabase
import com.joohnq.mood.database.StatsDatabaseSql
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test

class StatsRepositoryTest : RobolectricTests() {
    private lateinit var database: StatsDatabaseSql
    private lateinit var repository: StatsRepository

    @BeforeTest
    fun setUp() {
        val driver = createTestDriver(StatsDatabaseSql.Schema)
        database = StatsDatabase(driver).invoke()
        repository = StatsRepositoryImpl(database)
    }

    private suspend fun fillDumpItems() {
        items.forEach { repository.addStats(it) }
    }

    @Test
    fun `testing get stats`() =
        runBlocking {
            //GIVEN
            fillDumpItems()

            //WHEN
            val res = assertDoesNotThrow { repository.getStats() }
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
            val item = StatsRecord(
                id = 3,
                mood = Mood.Depressed,
                description = "description 3",
                date = LocalDateTime(2025, 1, 1, 0, 0, 0)
            )

            //WHEN
            assertDoesNotThrow { repository.addStats(item) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getStats() }.getOrThrow()

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
            assertDoesNotThrow { repository.deleteStat(1) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getStats() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 2 && it.mood == Mood.Happy && it.description == "description 2"
            }

            //WHEN
            assertDoesNotThrow { repository.deleteStat(2) }.getOrThrow()
            val items2 = assertDoesNotThrow { repository.getStats() }.getOrThrow()

            //THEN
            assertThat(items2).isEmpty()
        }

    companion object {
        val items = listOf(
            StatsRecord(
                id = 1,
                mood = Mood.Overjoyed,
                description = "description",
                date = LocalDateTime(2025, 1, 1, 0, 0, 0)
            ),
            StatsRecord(
                id = 2,
                mood = Mood.Happy,
                description = "description 2",
                date = LocalDateTime(2025, 1, 1, 0, 0, 0)
            )
        )
    }
}