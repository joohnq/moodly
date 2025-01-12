package com.joohnq.core.test

import com.varabyte.truthish.assertThat
import kotlin.test.fail

suspend fun <T> assertDoesNotThrow(block: suspend () -> T): T =
    try {
        block()
    } catch (e: Exception) {
        fail("An exception was thrown: ${e.message}")
    }

infix fun <T> List<T>.assertThatContains(comparator: (T) -> Boolean) {
    assertThat(find(comparator)).isNotNull()
}

infix fun <T> T.assertThatContains(comparator: (T) -> Boolean) {
    assertThat(comparator(this)).isTrue()
}