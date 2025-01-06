package com.joohnq.shared_resources

import androidx.compose.runtime.Composable

abstract class CustomScreen<T> {
    @Composable
    fun Content() {
        val state = Screen()
        UI(state)
    }

    @Composable
    abstract fun Screen(): T

    @Composable
    abstract fun UI(state: T)
}

abstract class CustomScreenNothing {
    @Composable
    fun Content() {
        Screen()
        UI()
    }

    @Composable
    abstract fun Screen()

    @Composable
    abstract fun UI()
}

abstract class CustomScreenNoUI {
    @Composable
    fun Content() {
        Screen()
    }

    @Composable
    abstract fun Screen()
}