package com.joohnq.stress_level.impl.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun StressLevelScreen(
    onNavigateAddStressLevel: () -> Unit,
    onGoBack: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val state by stressLevelViewModel.state.collectAsState()

    fun onEvent(event: StressLevelContract.Event) =
        when (event) {
            is StressLevelContract.Event.OnAddStressLevel -> onNavigateAddStressLevel()
            is StressLevelContract.Event.OnGoBack -> onGoBack()
        }

    return StressLevelContent(
        state = state,
        onEvent = ::onEvent,
        onAction = stressLevelViewModel::onIntent
    )
}