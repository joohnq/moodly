package com.joohnq.mood.data.converters

import com.joohnq.mood.domain.SleepInfluences
import com.varabyte.truthish.assertThat
import kotlin.test.Test
import kotlin.test.assertFails

class SleepInfluencesConverterTest {
    @Test
    fun `test fromSleepInfluences`() {
        val items = listOf(SleepInfluences.AlcoholConsumption, SleepInfluences.Caffeine)
        //8,4

        val res = SleepInfluencesConverter().fromSleepInfluences(items)

        assertThat(res).isEqualTo("[8,4]")
    }

    @Test
    fun `test toStressorsList`() {
        val items = "[8,4]"

        val res = SleepInfluencesConverter().toStressorsList(items)

        assertThat(res).isEqualTo(
            listOf(
                SleepInfluences.AlcoholConsumption,
                SleepInfluences.Caffeine
            )
        )
    }

    @Test
    fun `test toStressorsList with error`() {
        assertFails {
            SleepInfluencesConverter().toStressorsList("[8,4,10]")
        }
    }
}