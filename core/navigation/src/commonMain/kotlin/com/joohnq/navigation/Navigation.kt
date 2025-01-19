package com.joohnq.navigation

import androidx.navigation.NavHostController

fun NavHostController.onNavigate(destination: Destination, finish: Boolean = false) {
    navigate(destination) {
        if (finish) {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true
        }
    }
}

fun NavHostController.onNavigateBack(destination: Destination) {
    popBackStack(destination, inclusive = false)
}

fun NavHostController.onNavigateGraph(graph: NavigationGraph, finish: Boolean = false) {
    navigate(graph) {
        if (finish) {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true
        }
    }
}

fun NavHostController.onGoBack() {
    popBackStack()
}