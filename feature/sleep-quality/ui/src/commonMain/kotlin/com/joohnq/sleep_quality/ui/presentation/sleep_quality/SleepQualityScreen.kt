package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.joohnq.core.ui.mapper.getValueOrNull
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel

@Composable
fun SleepQualityScreen(
    id: Int,
    onNavigateAddSleepQuality: () -> Unit,
    onGoBack: () -> Unit,
) {
    val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val sleepQualityState by sleepQualityViewModel.state.collectAsState()
    val record by remember {
        mutableStateOf(
            sleepQualityState.sleepQualityRecords.getValueOrNull().find { it.id == id }
                ?: sleepQualityState.sleepQualityRecords.getValueOrNull().first()
        )
    }

    fun onEvent(event: SleepQualityEvent) =
        when (event) {
            SleepQualityEvent.OnAdd -> onNavigateAddSleepQuality()
            SleepQualityEvent.OnGoBack -> onGoBack()
        }

    return SleepQualityUI(
        record = record,
        onEvent = ::onEvent
    )
}
