package com.joohnq.moodapp.data.converters

import com.joohnq.moodapp.domain.StressLevel
import com.varabyte.truthish.assertThat
import kotlin.test.Test
import kotlin.test.assertFails

class StressLevelRecordConverterTest {
    @Test
    fun `test fromStressLevel`() {
        val item = StressLevel.One

        val res = StressLevelRecordConverter().fromStressLevel(item)

        assertThat(res).isEqualTo(1)
    }

    @Test
    fun `test toStressLevel`() {
        val item = 4

        val res = StressLevelRecordConverter().toStressLevel(item)

        assertThat(res).isEqualTo(StressLevel.Four)
    }

    @Test
    fun `test toStressLevel with error`() {
        assertFails {
            StressLevelRecordConverter().toStressLevel(10)
        }
    }
}