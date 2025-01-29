package com.joohnq.stress_level.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.joohnq.core.ui.mapper.getValueOrNull
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel

@Composable
fun StressLevelScreen(
    id: Int,
    onNavigateAddStressLevel: () -> Unit,
    onGoBack: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val state by stressLevelViewModel.state.collectAsState()
    var record by remember {
        mutableStateOf(state.stressLevelRecords.getValueOrNull().find { it.id == id }
            ?: state.stressLevelRecords.getValueOrNull().first())
    }

    fun onEvent(event: StressLevelEvent) =
        when (event) {
            is StressLevelEvent.OnAdd -> onNavigateAddStressLevel()
            is StressLevelEvent.OnGoBack -> onGoBack()
        }

    return StressLevelUI(
        record = record,
        onEvent = ::onEvent
    )
}
