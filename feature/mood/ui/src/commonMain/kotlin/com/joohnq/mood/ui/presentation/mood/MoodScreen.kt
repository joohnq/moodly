package com.joohnq.mood.ui.presentation.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.joohnq.core.ui.mapper.getValueOrNull
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.use_case.GetNextStatUseCase
import com.joohnq.mood.domain.use_case.GetPreviousStatUseCase
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import org.koin.compose.koinInject

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
        statsRecords = statsState.statsRecords,
        onEvent = ::onEvent
    )
}
