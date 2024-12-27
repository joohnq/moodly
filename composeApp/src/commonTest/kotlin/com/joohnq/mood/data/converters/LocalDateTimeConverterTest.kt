package com.joohnq.mood.data.converters

import com.varabyte.truthish.assertThat
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test

class LocalDateTimeConverterTest {
    @Test
    fun `test fromLocalDateTime`() {
        val datetime = LocalDateTime(2024, 11, 28, 8, 6, 30)

        val res = LocalDateTimeConverter().fromLocalDateTime(datetime)

        assertThat(res).isEqualTo("2024-11-28T08:06:30")
    }

    @Test
    fun `test toLocalDateTime`() {
        val datetime = "2024-11-28T08:06:30"

        val res = LocalDateTimeConverter().toLocalDateTime(datetime)

        assertThat(res).isEqualTo(LocalDateTime(2024, 11, 28, 8, 6, 30))
    }
}