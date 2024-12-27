package com.joohnq.mood.data.repository

import com.joohnq.mood.data.dao.StressLevelRecordDAO
import com.joohnq.mood.domain.StressLevelRecord
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

class StressLevelRepositoryTest {
    private lateinit var stressLevelRecordDAO: StressLevelRecordDAO
    private lateinit var stressLevelRepository: StressLevelRepository

    @BeforeTest
    fun setUp() {
        stressLevelRecordDAO = mock(MockMode.autofill)
        stressLevelRepository = StressLevelRepositoryImpl(stressLevelRecordDAO)
    }

    private fun mockList(count: Int): List<StressLevelRecord> {
        val list = mutableListOf<StressLevelRecord>()
        for (i in 0..count) {
            list.add(StressLevelRecord.init())
        }
        return list
    }

    @Test
    fun `test getStressLevels with a filled list - should return the list size`() = runTest {
        //GIVEN
        val list = mockList(10)
        everySuspend { stressLevelRecordDAO.getStressLevels() } returns list

        //WHEN
        val res = stressLevelRepository.getStressLevels()

        //THEN
        assertThat(res).isNotEmpty()
        assertThat(res).isEqualTo(list)
        assertThat(res.size).isEqualTo(list.size)
    }

    @Test
    fun `test getStressLevels with a empty list - should be empty`() = runTest {
        //GIVEN
        val list = emptyList<StressLevelRecord>()
        everySuspend { stressLevelRecordDAO.getStressLevels() } returns list

        //WHEN
        val res = stressLevelRepository.getStressLevels()

        //THEN
        assertThat(res).isEmpty()
        assertThat(res).isEqualTo(list)
        assertThat(res.size).isEqualTo(list.size)
    }

    @Test
    fun `test addStressLevel with a success execution - should return true`() = runTest {
        //GIVEN
        val item =
            StressLevelRecord.Builder().build()
        everySuspend {
            stressLevelRecordDAO.addStressLevel(any<StressLevelRecord>())
        } returns Unit

        //WHEN
        val res = stressLevelRepository.addStressLevel(item)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            stressLevelRecordDAO.addStressLevel(any<StressLevelRecord>())
        }
    }

    @Test
    fun `test addSleepQuality with a failed execution - should return false`() = runTest {
        //GIVEN
        val item =
            StressLevelRecord.Builder().build()
        everySuspend {
            stressLevelRecordDAO.addStressLevel(any<StressLevelRecord>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = stressLevelRepository.addStressLevel(item)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            stressLevelRecordDAO.addStressLevel(any<StressLevelRecord>())
        }
    }

}