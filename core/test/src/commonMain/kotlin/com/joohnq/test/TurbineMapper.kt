package com.joohnq.test

import app.cash.turbine.TurbineTestContext
import com.varabyte.truthish.assertThat

/**
 * Helper function to assert the expected UI state
 */
private suspend fun <T> TurbineTestContext<T>.assertUiState(expected: T) {
    assertThat(awaitItem()).isEqualTo(expected)
}

/**
 * Helper function to assert the expected UI state
 */
private suspend fun <T> TurbineTestContext<T>.assertState(field: (T) -> Any, expected: Any) {
    assertThat(field(awaitItem())).isEqualTo(expected)
}

/**
 * Helper function to assert the expected UI state
 */
private suspend fun <T> TurbineTestContext<T>.assertSideEffect(expected: Any) {
    assertThat(awaitItem()).isEqualTo(expected)
}
