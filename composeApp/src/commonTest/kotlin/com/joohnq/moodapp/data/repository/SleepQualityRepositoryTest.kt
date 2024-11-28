package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.data.dao.SleepQualityRecordDAO
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SleepQualityRepositoryTest {
    private lateinit var sleepQualityRecordDAO: SleepQualityRecordDAO
    private lateinit var sleepQualityRepository: SleepQualityRepository

    @BeforeTest
    fun setUp() {
        sleepQualityRecordDAO = mock(MockMode.autofill)
        sleepQualityRepository = SleepQualityRepositoryImpl(sleepQualityRecordDAO)
    }

    private fun mockList(count: Int): List<SleepQualityRecord> {
        val list = mutableListOf<SleepQualityRecord>()
        for (i in 0..count) {
            list.add(SleepQualityRecord.init())
        }
        return list
    }

    @Test
    fun `test getSleepQualities with a filled list - should return the list size`() = runTest {
        //GIVEN
        val list = mockList(10)
        everySuspend { sleepQualityRecordDAO.getSleepQualities() } returns list

        //WHEN
        val res = sleepQualityRepository.getSleepQualities()

        //THEN
        assertThat(res).isNotEmpty()
        assertThat(res).isEqualTo(list)
        assertThat(res.size).isEqualTo(list.size)
    }

    @Test
    fun `test getHealthJournals with a empty list - should be empty`() = runTest {
        //GIVEN
        val list = emptyList<SleepQualityRecord>()
        everySuspend { sleepQualityRecordDAO.getSleepQualities() } returns list

        //WHEN
        val res = sleepQualityRepository.getSleepQualities()

        //THEN
        assertThat(res).isEmpty()
        assertThat(res).isEqualTo(list)
        assertThat(res.size).isEqualTo(list.size)
    }

    @Test
    fun `test addSleepQuality with a success execution - should return true`() = runTest {
        //GIVEN
        val item =
            SleepQualityRecord.Builder().build()
        everySuspend {
            sleepQualityRecordDAO.addSleepQuality(any<SleepQualityRecord>())
        } returns Unit

        //WHEN
        val res = sleepQualityRepository.addSleepQuality(item)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            sleepQualityRecordDAO.addSleepQuality(any<SleepQualityRecord>())
        }
    }

    @Test
    fun `test addSleepQuality with a failed execution - should return false`() = runTest {
        //GIVEN
        val item =
            SleepQualityRecord.Builder().build()
        everySuspend {
            sleepQualityRecordDAO.addSleepQuality(any<SleepQualityRecord>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = sleepQualityRepository.addSleepQuality(item)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            sleepQualityRecordDAO.addSleepQuality(any<SleepQualityRecord>())
        }
    }

}