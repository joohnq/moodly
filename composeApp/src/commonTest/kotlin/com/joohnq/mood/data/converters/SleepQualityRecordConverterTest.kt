package com.joohnq.mood.data.converters

import com.joohnq.mood.domain.SleepQuality
import com.varabyte.truthish.assertThat
import kotlin.test.Test
import kotlin.test.assertFails

class SleepQualityRecordConverterTest {
    @Test
    fun `test fromSleepQuality`() {
        val item = SleepQuality.Fair

        val res = SleepQualityRecordConverter().fromSleepQuality(item)

        assertThat(res).isEqualTo(3)
    }

    @Test
    fun `test toSleepQuality`() {
        val item = 4

        val res = SleepQualityRecordConverter().toSleepQuality(item)

        assertThat(res).isEqualTo(SleepQuality.Good)
    }

    @Test
    fun `test toSleepQuality with error`() {
        assertFails {
            SleepQualityRecordConverter().toSleepQuality(10)
        }
    }
}