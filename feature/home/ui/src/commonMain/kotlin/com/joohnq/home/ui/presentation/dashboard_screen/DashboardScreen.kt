package com.joohnq.home.ui.presentation.dashboard_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.joohnq.home.ui.components.DashboardBottomNavigation
import com.joohnq.home.ui.presentation.home.HomeScreen
import com.joohnq.mood.CustomScreenNothing

class DashboardScreen : CustomScreenNothing() {
    @Composable
    override fun Screen() {
    }

    @Composable
    override fun UI() {
        TabNavigator(HomeScreen()) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { _ ->
                CurrentTab()
                DashboardBottomNavigation(
                    onNavigateScreen = { screen -> onNavigate(screen) },
                )
            }
        }
    }
}