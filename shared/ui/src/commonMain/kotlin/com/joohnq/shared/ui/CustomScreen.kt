package com.joohnq.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

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

    fun onGoBack(screen: Screen) {
        navigator.popUntil { it == screen }
    }

    @Composable
    abstract fun Screen(): T

    @Composable
    abstract fun UI(state: T)
}

abstract class CustomTab<T> : Tab {
    private var _tabNavigator: TabNavigator? = null
    private val tabNavigator: TabNavigator get() = _tabNavigator!!

    private var _navigator: Navigator? = null
    private val navigator: Navigator get() = _navigator!!

    @Composable
    override fun Content() {
        _tabNavigator = LocalTabNavigator.current
        _navigator = LocalNavigator.currentOrThrow.parent
        DisposableEffect(Unit) {
            onDispose {
                _tabNavigator = null
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

    fun onNavigateTab(screen: Tab) {
        tabNavigator.current = screen
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