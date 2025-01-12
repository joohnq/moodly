package com.joohnq.stress_level.data.repository

import com.joohnq.core.test.RobolectricTests
import com.joohnq.core.test.assertDoesNotThrow
import com.joohnq.core.test.assertThatOneOfContains
import com.joohnq.core.test.createTestDriver
import com.joohnq.stress_level.data.database.StressLevelDatabase
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.domain.repository.StressLevelRepository
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test

class StressLevelRepositoryTest : RobolectricTests() {
    private lateinit var database: StressLevelDatabaseSql
    private lateinit var repository: StressLevelRepository

    @BeforeTest
    fun setUp() {
        val driver = createTestDriver(StressLevelDatabaseSql.Schema)
        database = StressLevelDatabase(driver).invoke()
        repository = StressLevelRepositoryImpl(database)
    }

    private suspend fun fillDumpItems() {
        items.forEach { repository.addStressLevel(it) }
    }

    @Test
    fun `testing get stress level`() =
        runBlocking {
            //GIVEN
            fillDumpItems()

            //WHEN
            val items = assertDoesNotThrow { repository.getStressLevels() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 1 && it.stressLevel == StressLevel.Three && it.stressors.isEmpty()
            }
            items assertThatOneOfContains {
                it.id == 2 && it.stressLevel == StressLevel.One && it.stressors == listOf(
                    Stressor.Work
                )
            }
        }

    @Test
    fun `testing add stress level`() =
        runBlocking {
            //WHEN
            assertDoesNotThrow { repository.addStressLevel(items[0]) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getStressLevels() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 1 && it.stressLevel == StressLevel.Three && it.stressors.isEmpty()
            }
        }

    companion object {
        val items = listOf(
            StressLevelRecord(
                id = 1,
                stressLevel = StressLevel.Three,
                stressors = listOf(),
                date = LocalDateTime(2025, 1, 1, 0, 0, 0),
            ),
            StressLevelRecord(
                id = 2,
                stressLevel = StressLevel.One,
                stressors = listOf(
                    Stressor.Work
                ),
                date = LocalDateTime(2025, 1, 1, 0, 0, 0),
            )
        )
    }
}