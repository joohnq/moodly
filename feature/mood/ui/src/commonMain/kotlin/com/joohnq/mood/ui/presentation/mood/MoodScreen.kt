package com.joohnq.mood.ui.presentation.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.use_case.GetNextStatUseCase
import com.joohnq.mood.domain.use_case.GetPreviousStatUseCase
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.presentation.mood.state.MoodState
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.shared.ui.state.UiState.Companion.getValue
import com.joohnq.shared.ui.state.UiState.Companion.getValueOrNull
import org.koin.compose.koinInject

class MoodScreen(val id: Int? = null) : CustomScreen<MoodState>() {
    @Composable
    override fun Screen(): MoodState {
        val statsViewModel: StatsViewModel = sharedViewModel()
        val statsState by statsViewModel.state.collectAsState()
        val getNextStatUseCase: GetNextStatUseCase = koinInject()
        val getPreviousStatUseCase: GetPreviousStatUseCase = koinInject()
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
                is MoodEvent.OnAddStatScreen -> {}
//                    onNavigate(AddStatScreen())
                is MoodEvent.OnSetMood -> {
                    currentStatsRecord = event.statsRecord
                }
            }

        LaunchedEffect(currentStatsRecord) {
            currentStatsRecord?.let {
                hasNext =
                    getNextStatUseCase(
                        it,
                        statsState.statsRecords.getValue()
                    )

                hasPrevious =
                    getPreviousStatUseCase(
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
