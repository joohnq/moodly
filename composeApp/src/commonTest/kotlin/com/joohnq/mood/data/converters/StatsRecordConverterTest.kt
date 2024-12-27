package com.joohnq.mood.data.converters

import com.joohnq.mood.domain.Mood
import com.varabyte.truthish.assertThat
import kotlin.test.Test
import kotlin.test.assertFails

class StatsRecordConverterTest {
    @Test
    fun `test fromMood`() {
        val item = Mood.Sad

        val res = StatsRecordConverter().fromMood(item)

        assertThat(res).isEqualTo(1)
    }

    @Test
    fun `test toMood`() {
        val item = 4

        val res = StatsRecordConverter().toMood(item)

        assertThat(res).isEqualTo(Mood.Overjoyed)
    }

    @Test
    fun `test toMood with error`() {
        assertFails {
            StatsRecordConverter().toMood(10)
        }
    }
}