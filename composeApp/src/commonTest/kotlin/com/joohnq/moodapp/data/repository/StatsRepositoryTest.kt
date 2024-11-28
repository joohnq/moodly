package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.data.dao.StatsRecordDAO
import com.joohnq.moodapp.domain.StatsRecord
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

class StatsRepositoryTest {
    private lateinit var statsRecordDAO: StatsRecordDAO
    private lateinit var statsRepository: StatsRepository

    @BeforeTest
    fun setUp() {
        statsRecordDAO = mock(MockMode.autofill)
        statsRepository = StatsRepositoryImpl(statsRecordDAO)
    }

    private fun mockList(count: Int): List<StatsRecord> {
        val list = mutableListOf<StatsRecord>()
        for (i in 0..count) {
            list.add(StatsRecord.init())
        }
        return list
    }

    @Test
    fun `test getStats with a filled list - should return the list size`() = runTest {
        //GIVEN
        val list = mockList(10)
        everySuspend { statsRecordDAO.getStats() } returns list

        //WHEN
        val res = statsRepository.getStats()

        //THEN
        assertThat(res).isNotEmpty()
        assertThat(res).isEqualTo(list)
        assertThat(res.size).isEqualTo(list.size)
    }

    @Test
    fun `test getStats with a empty list - should be empty`() = runTest {
        //GIVEN
        val list = emptyList<StatsRecord>()
        everySuspend { statsRecordDAO.getStats() } returns list

        //WHEN
        val res = statsRepository.getStats()

        //THEN
        assertThat(res).isEmpty()
        assertThat(res).isEqualTo(list)
        assertThat(res.size).isEqualTo(list.size)
    }

    @Test
    fun `test addStats with a success execution - should return true`() = runTest {
        //GIVEN
        val item =
            StatsRecord.Builder().build()
        everySuspend {
            statsRecordDAO.addStats(any<StatsRecord>())
        } returns Unit

        //WHEN
        val res = statsRepository.addStats(item)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            statsRecordDAO.addStats(any<StatsRecord>())
        }
    }

    @Test
    fun `test addSleepQuality with a failed execution - should return false`() = runTest {
        //GIVEN
        val item =
            StatsRecord.Builder().build()
        everySuspend {
            statsRecordDAO.addStats(any<StatsRecord>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = statsRepository.addStats(item)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            statsRecordDAO.addStats(any<StatsRecord>())
        }
    }

}