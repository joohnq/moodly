package com.joohnq.mood.data.converters

import com.joohnq.mood.domain.Stressor
import com.varabyte.truthish.assertThat
import kotlin.test.Test

class StressorsConverterTest {
    @Test
    fun `test fromStressorsList`() {
        val items = listOf(Stressor.Kids, Stressor.Work)

        val res = StressorsConverter().fromStressorsList(items)

        assertThat(res).isEqualTo("[\"2\",\"0\"]")
    }

    @Test
    fun `test fromStressorsList with other`() {
        val items = listOf(Stressor.Kids, Stressor.Other("other"))

        val res = StressorsConverter().fromStressorsList(items)

        assertThat(res).isEqualTo("[\"2\",\"other\"]")
    }

    @Test
    fun `test toStressorsList`() {
        val items = "[\"5\",\"4\"]"

        val res = StressorsConverter().toStressorsList(items)

        assertThat(res).isEqualTo(listOf(Stressor.Loneliness, Stressor.Finances))
    }

    @Test
    fun `test toStressorsList with other`() {
        val items = "[\"5\",\"2\",\"oi\"]"

        val res = StressorsConverter().toStressorsList(items)

        assertThat(res).isEqualTo(
            listOf(
                Stressor.Loneliness,
                Stressor.Kids,
                Stressor.Other("oi")
            )
        )
    }
}