package com.joohnq.moodapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

abstract class CustomScreen<T> : Screen {
    private var _navigator: Navigator? = null
    private val navigator: Navigator get() = _navigator!!

    @Composable
    override fun Content() {
        _navigator = LocalNavigator.currentOrThrow
        DisposableEffect(Unit) {
            onDispose {
                _navigator = null
            }
        }
        val state = Screen()
        UI(state)
    }

    fun onNavigate(screen: Screen, finish: Boolean = false) {
        if (finish) {
            navigator.replaceAll(screen)
        } else {
            navigator.push(screen)
        }
    }

    fun onGoBack() {
        navigator.pop()
    }

    @Composable
    abstract fun Screen(): T

    @Composable
    abstract fun UI(state: T)
}

abstract class CustomScreenNothing : Screen {
    private var _navigator: Navigator? = null
    private val navigator: Navigator get() = _navigator!!

    @Composable
    override fun Content() {
        _navigator = LocalNavigator.currentOrThrow
        DisposableEffect(Unit) {
            onDispose {
                _navigator = null
            }
        }
        Screen()
        UI()
    }

    fun onNavigate(screen: Screen, finish: Boolean = false) {
        if (finish) {
            navigator.replaceAll(screen)
        } else {
            navigator.push(screen)
        }
    }

    @Composable
    abstract fun Screen()

    @Composable
    abstract fun UI()
}