package com.joohnq.moodapp.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject

abstract class BasicScreen : Screen {
    lateinit var navigator: Navigator
        private set
    lateinit var scope: CoroutineScope
        private set
    lateinit var ioDispatcher: CoroutineDispatcher
        private set

    @Composable
    final override fun Content() {
        InitializeDependencies()
        Init()
    }

    @Composable
    private fun InitializeDependencies() {
        navigator = LocalNavigator.currentOrThrow
        scope = rememberCoroutineScope()
        ioDispatcher = koinInject()
    }

    @Composable
    protected open fun Init() {
    }
}