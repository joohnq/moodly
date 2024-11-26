package com.joohnq.moodapp.ui.presentation.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_stats.AddStatScreen
import com.joohnq.moodapp.ui.presentation.home.HomeScreen
import com.joohnq.moodapp.ui.presentation.mood.event.MoodEvent
import com.joohnq.moodapp.ui.presentation.mood.state.MoodState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.util.helper.StatsManager
import com.joohnq.moodapp.viewmodel.StatsViewModel

class MoodScreen(val id: Int? = null) : CustomScreen<MoodState>() {
    @Composable
    override fun Screen(): MoodState {
        val statsViewModel: StatsViewModel = sharedViewModel()
        val statsState by statsViewModel.statsState.collectAsState()
        var currentStatsRecord by remember {
            mutableStateOf(
                statsState.statsRecords.getValue().find { it.id == id }
                    ?: statsState.statsRecords.getValue().first()
            )
        }
        var hasNext by remember { mutableStateOf<StatsRecord?>(null) }
        var hasPrevious by remember { mutableStateOf<StatsRecord?>(null) }

        fun onEvent(event: MoodEvent) =
            when (event) {
                is MoodEvent.OnGoBack -> onGoBack(HomeScreen())
                is MoodEvent.OnNext -> hasNext?.run { hasNext = this }
                is MoodEvent.OnPrevious -> hasPrevious?.run { hasPrevious = this }
                is MoodEvent.OnAddStatScreen -> onNavigate(AddStatScreen())
                is MoodEvent.OnSetMood -> {
                    currentStatsRecord = event.statsRecord
                }
            }

        LaunchedEffect(currentStatsRecord) {
            hasNext =
                StatsManager.getNext(
                    currentStatsRecord,
                    statsState.statsRecords.getValue()
                )

            hasPrevious =
                StatsManager.getPrevious(
                    currentStatsRecord,
                    statsState.statsRecords.getValue()
                )
        }

        return MoodState(
            statsRecord = currentStatsRecord,
            hasNext = hasNext != null,
            hasPrevious = hasPrevious != null,
            statsRecords = statsState.statsRecords.getValue().reversed(),
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: MoodState) = MoodUI(state)
}
