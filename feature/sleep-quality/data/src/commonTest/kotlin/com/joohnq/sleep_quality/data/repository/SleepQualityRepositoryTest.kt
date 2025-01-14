package com.joohnq.sleep_quality.data.repository

import com.joohnq.core.test.CoreTestConstants
import com.joohnq.core.test.RobolectricTests
import com.joohnq.core.test.assertDoesNotThrow
import com.joohnq.core.test.assertThatOneOfContains
import com.joohnq.core.test.createTestDriver
import com.joohnq.sleep_quality.data.database.SleepQualityDatabase
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.domain.entity.SleepInfluences
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class SleepQualityRepositoryTest : RobolectricTests() {
    private lateinit var database: SleepQualityDatabaseSql
    private lateinit var repository: SleepQualityRepository

    @BeforeTest
    fun setUp() {
        val driver = createTestDriver(SleepQualityDatabaseSql.Schema)
        database = SleepQualityDatabase(driver).invoke()
        repository = SleepQualityRepositoryImpl(database)
    }

    private suspend fun fillDumpItems() {
        items.forEach { repository.addSleepQuality(it) }
    }

    @Test
    fun `testing get sleep quality`() =
        runBlocking {
            //GIVEN
            fillDumpItems()

            //WHEN
            val items = assertDoesNotThrow { repository.getSleepQualities() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 1 && it.sleepQuality == SleepQuality.Excellent && it.startSleeping == "00:00" && it.endSleeping == "08:00" && it.sleepInfluences.isEmpty()
            }
            items assertThatOneOfContains {
                it.id == 2 && it.sleepQuality == SleepQuality.Good && it.startSleeping == "22:00" && it.endSleeping == "06:00" && it.sleepInfluences == listOf(
                    SleepInfluences.Anxiety
                )
            }
        }

    @Test
    fun `testing add sleep quality`() =
        runBlocking {
            //WHEN
            assertDoesNotThrow { repository.addSleepQuality(items[0]) }.getOrThrow()
            val items = assertDoesNotThrow { repository.getSleepQualities() }.getOrThrow()

            //THEN
            assertThat(items.isNotEmpty())
            items assertThatOneOfContains {
                it.id == 1 && it.sleepQuality == SleepQuality.Excellent && it.startSleeping == "00:00" && it.endSleeping == "08:00" && it.sleepInfluences.isEmpty()
            }
        }

    companion object {
        val items = listOf(
            SleepQualityRecord(
                id = 1,
                sleepQuality = SleepQuality.Excellent,
                startSleeping = "00:00",
                endSleeping = "08:00",
                sleepInfluences = listOf(),
                date = CoreTestConstants.FAKE_DATE,
            ),
            SleepQualityRecord(
                id = 2,
                sleepQuality = SleepQuality.Good,
                startSleeping = "22:00",
                endSleeping = "06:00",
                sleepInfluences = listOf(
                    SleepInfluences.Anxiety
                ),
                date = CoreTestConstants.FAKE_DATE,
            )
        )
    }
}