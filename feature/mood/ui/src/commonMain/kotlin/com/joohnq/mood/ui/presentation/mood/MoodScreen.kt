package com.joohnq.mood.ui.presentation.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.viewmodel.StatsViewModel

@Composable
fun MoodScreen(
    onNavigateBackToHome: () -> Unit,
    onNavigateAddStat: () -> Unit,
) {
    val statsViewModel: StatsViewModel = sharedViewModel()
    val statsState by statsViewModel.state.collectAsState()

    fun onEvent(event: MoodEvent) =
        when (event) {
            is MoodEvent.OnGoBack -> onNavigateBackToHome()
            is MoodEvent.OnAddStatScreen -> onNavigateAddStat()
        }

    MoodUI(
        records = statsState.records,
        onEvent = ::onEvent
    )
}
