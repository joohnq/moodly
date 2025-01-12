package com.joohnq.core.test

interface CustomFake {
    var shouldThrowError: Boolean

    fun updateShouldThrowError(shouldThrowError: Boolean) {
        this.shouldThrowError = shouldThrowError
    }
}