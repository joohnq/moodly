package com.joohnq.mood.ui.presentation.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.joohnq.domain.entity.StatsRecord
import com.joohnq.mood.CustomScreen
import com.joohnq.mood.sharedViewModel
import com.joohnq.mood.state.UiState.Companion.getValue
import com.joohnq.mood.state.UiState.Companion.getValueOrNull
import com.joohnq.mood.ui.presentation.add_stats.AddStatScreen
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.presentation.mood.state.MoodState
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.mood.util.helper.StatsManager

class MoodScreen(val id: Int? = null) : CustomScreen<MoodState>() {
    @Composable
    override fun Screen(): MoodState {
        val statsViewModel: StatsViewModel = sharedViewModel()
        val statsState by statsViewModel.statsState.collectAsState()
        var currentStatsRecord by remember {
            mutableStateOf(
                statsState.statsRecords.getValueOrNull()?.find { it.id == id }
                    ?: statsState.statsRecords.getValueOrNull()?.first()
            )
        }
        var hasNext by remember { mutableStateOf<StatsRecord?>(null) }
        var hasPrevious by remember { mutableStateOf<StatsRecord?>(null) }

        fun onEvent(event: MoodEvent) =
            when (event) {
                is MoodEvent.OnGoBack -> {}
//                    onGoBack(HomeScreen())
                is MoodEvent.OnNext -> hasNext?.run { currentStatsRecord = this }
                is MoodEvent.OnPrevious -> hasPrevious?.run { currentStatsRecord = this }
                is MoodEvent.OnAddStatScreen -> onNavigate(AddStatScreen())
                is MoodEvent.OnSetMood -> {
                    currentStatsRecord = event.statsRecord
                }
            }

        LaunchedEffect(currentStatsRecord) {
            currentStatsRecord?.let {
                hasNext =
                    StatsManager.getNext(
                        it,
                        statsState.statsRecords.getValue()
                    )

                hasPrevious =
                    StatsManager.getPrevious(
                        it,
                        statsState.statsRecords.getValue()
                    )
            }
        }

        return MoodState(
            statsRecord = currentStatsRecord,
            hasNext = hasNext != null,
            hasPrevious = hasPrevious != null,
            statsRecords = statsState.statsRecords,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: MoodState) = MoodUI(state)
}
