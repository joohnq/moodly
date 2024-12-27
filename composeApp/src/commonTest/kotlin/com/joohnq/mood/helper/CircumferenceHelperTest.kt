package com.joohnq.mood.helper

import com.joohnq.mood.util.mappers.toDegrees
import com.joohnq.mood.util.mappers.toRadians
import com.varabyte.truthish.assertThat
import kotlin.test.Test

class CircumferenceHelperTest {
    @Test
    fun testToRadius() {
        val value = 100.0
        val res = value.toRadians()
        assertThat(res).isEqualTo(1.7453292519943295)
    }

    @Test
    fun testToDegrees() {
        val value = 0.62
        val res = value.toDegrees()
        assertThat(res).isEqualTo(35.523383298111035)
    }
}