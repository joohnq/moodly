package com.joohnq.navigation

import kotlinx.serialization.Serializable

sealed class NavigationGraph {
    @Serializable data object Loading : NavigationGraph()
    @Serializable data object Welcome : NavigationGraph()
    @Serializable data object Onboarding : NavigationGraph()
    @Serializable data object Auth : NavigationGraph()
    @Serializable data object App : NavigationGraph()
}