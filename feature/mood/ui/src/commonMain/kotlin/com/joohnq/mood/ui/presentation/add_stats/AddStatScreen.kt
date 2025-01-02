package com.joohnq.mood.ui.presentation.add_stats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.mood.ui.presentation.add_stats.event.AddStatEvent
import com.joohnq.mood.ui.presentation.add_stats.state.AddStatState
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatViewModel
import com.joohnq.mood.ui.presentation.expression_analysis.ExpressionAnalysisScreen
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.shared.ui.state.UiState.Companion.getValue
import com.joohnq.user.ui.viewmodel.user.UserViewModel

class AddStatScreen : CustomScreen<AddStatState>() {
    @Composable
    override fun Screen(): AddStatState {
        val addStatsViewModel: AddStatViewModel = sharedViewModel()
        val userViewModel: UserViewModel = sharedViewModel()
        val addStatsState by addStatsViewModel.state.collectAsState()
        val userState by userViewModel.state.collectAsState()

        fun onEvent(event: AddStatEvent) =
            when (event) {
                AddStatEvent.OnGoBack -> onGoBack()
                AddStatEvent.OnNavigateToExpressionAnalysis -> {}
//                    onNavigate(ExpressionAnalysisScreen())
            }

        return AddStatState(
            username = userState.user.getValue().name,
            selectedMood = addStatsState.mood,
            onEvent = ::onEvent,
            onAddAction = addStatsViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: AddStatState) = AddStatScreenUI(state)
}
