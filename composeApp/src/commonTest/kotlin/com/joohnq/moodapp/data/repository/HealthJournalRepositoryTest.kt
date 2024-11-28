package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.data.dao.HealthJournalRecordDAO
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.Stressor
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

class HealthJournalRepositoryTest {
    private lateinit var healthJournalRecordDAO: HealthJournalRecordDAO
    private lateinit var healthJournalRepository: HealthJournalRepository

    @BeforeTest
    fun setUp() {
        healthJournalRecordDAO = mock(MockMode.autofill)
        healthJournalRepository = HealthJournalRepositoryImpl(healthJournalRecordDAO)
    }

    private fun mockList(count: Int): List<HealthJournalRecord> {
        val list = mutableListOf<HealthJournalRecord>()
        for (i in 0..count) {
            list.add(HealthJournalRecord.init())
        }
        return list
    }

    @Test
    fun `test getHealthJournals with a filled list - should return the list size`() = runTest {
        //GIVEN
        val list = mockList(10)
        everySuspend { healthJournalRecordDAO.getHealthJournals() } returns list

        //WHEN
        val res = healthJournalRepository.getHealthJournals()

        //THEN
        assertThat(res).isNotEmpty()
        assertThat(res).isEqualTo(list)
        assertThat(res.size).isEqualTo(list.size)
    }

    @Test
    fun `test getHealthJournals with a empty list - should be empty`() = runTest {
        //GIVEN
        val list = emptyList<HealthJournalRecord>()
        everySuspend { healthJournalRecordDAO.getHealthJournals() } returns list

        //WHEN
        val res = healthJournalRepository.getHealthJournals()

        //THEN
        assertThat(res).isEmpty()
        assertThat(res).isEqualTo(list)
        assertThat(res.size).isEqualTo(list.size)
    }

    @Test
    fun `test addHealthJournal with a success execution - should return true`() = runTest {
        //GIVEN
        val item =
            HealthJournalRecord.Builder().setStressors(listOf(Stressor.Kids, Stressor.Work)).build()
        everySuspend {
            healthJournalRecordDAO.addHealthJournal(any<HealthJournalRecord>())
        } returns Unit

        //WHEN
        val res = healthJournalRepository.addHealthJournal(item)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            healthJournalRecordDAO.addHealthJournal(any<HealthJournalRecord>())
        }
    }

    @Test
    fun `test addHealthJournal with a failed execution - should return false`() = runTest {
        //GIVEN
        val item =
            HealthJournalRecord.Builder().setStressors(listOf(Stressor.Kids, Stressor.Work)).build()

        everySuspend {
            healthJournalRecordDAO.addHealthJournal(any<HealthJournalRecord>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = healthJournalRepository.addHealthJournal(item)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            healthJournalRecordDAO.addHealthJournal(any<HealthJournalRecord>())
        }
    }

    @Test
    fun `test deleteHealthJournal with a success execution - should return true`() = runTest {
        //GIVEN
        val id = 1
        everySuspend {
            healthJournalRecordDAO.deleteHealthJournal(any<Int>())
        } returns Unit

        //WHEN
        val res = healthJournalRepository.deleteHealthJournal(id)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            healthJournalRecordDAO.deleteHealthJournal(id)
        }
    }

    @Test
    fun `test deleteHealthJournal with a failed execution - should return false`() = runTest {
        //GIVEN
        val id = 1
        everySuspend {
            healthJournalRecordDAO.deleteHealthJournal(any<Int>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = healthJournalRepository.deleteHealthJournal(id)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            healthJournalRecordDAO.deleteHealthJournal(id)
        }
    }

    @Test
    fun `test updateHealthJournal with a success execution - should return true`() = runTest {
        //GIVEN
        val item =
            HealthJournalRecord.Builder().setStressors(listOf(Stressor.Kids, Stressor.Work)).build()
        everySuspend {
            healthJournalRecordDAO.updateHealthJournal(any<HealthJournalRecord>())
        } returns Unit

        //WHEN
        val res = healthJournalRepository.updateHealthJournal(item)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            healthJournalRecordDAO.updateHealthJournal(item)
        }
    }

    @Test
    fun `test updateHealthJournal with a failed execution - should return false`() = runTest {
        //GIVEN
        val item =
            HealthJournalRecord.Builder().setStressors(listOf(Stressor.Kids, Stressor.Work)).build()
        everySuspend {
            healthJournalRecordDAO.updateHealthJournal(any<HealthJournalRecord>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = healthJournalRepository.updateHealthJournal(item)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            healthJournalRecordDAO.updateHealthJournal(item)
        }
    }
}