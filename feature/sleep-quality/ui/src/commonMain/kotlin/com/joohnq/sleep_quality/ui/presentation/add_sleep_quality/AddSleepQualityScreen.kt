package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.mapper.fold
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.mood.ui.mapper.toSleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.mapper.endSleeping
import com.joohnq.sleep_quality.domain.mapper.startSleeping
import com.joohnq.sleep_quality.ui.mapper.toDomain
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.event.AddSleepQualityEvent
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.state.AddSleepQualityState
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityIntent
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityViewModel
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import kotlinx.coroutines.launch

class AddSleepQualityScreen(
    private val onGoBack: () -> Unit,
) : CustomScreen<AddSleepQualityState>() {
    @Composable
    override fun Screen(): AddSleepQualityState {
        val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
        val addSleepQualityViewModel: AddSleepQualityViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }
        val sleepQualityState by sleepQualityViewModel.state.collectAsState()
        val addSleepQualityState by addSleepQualityViewModel.state.collectAsState()

        fun onError(message: String) {
            scope.launch { snackBarState.showSnackbar(message) }
        }

        fun onEvent(event: AddSleepQualityEvent) =
            when (event) {
                AddSleepQualityEvent.OnGoBack -> onGoBack()
                AddSleepQualityEvent.OnAdd ->
                    sleepQualityViewModel.onAction(
                        SleepQualityIntent.AddSleepQualityRecord(
                            SleepQualityRecord(
                                sleepQuality = addSleepQualityState.mood!!.toSleepQuality(),
                                sleepInfluences = addSleepQualityState.selectedSleepInfluences.toDomain(),
                            ).startSleeping(
                                addSleepQualityState.startHour,
                                addSleepQualityState.startMinute
                            ).endSleeping(
                                addSleepQualityState.endHour,
                                addSleepQualityState.endMinute
                            )
                        )
                    )
            }

        LaunchedEffect(sleepQualityState.adding) {
            sleepQualityState.adding.fold(
                onError = ::onError,
                onSuccess = {
                    onEvent(AddSleepQualityEvent.OnGoBack)
                    sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
                    sleepQualityViewModel.onAction(SleepQualityIntent.ResetAddingStatus)
                },
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                addSleepQualityViewModel.onAction(AddSleepQualityIntent.ResetState)
            }
        }

        return AddSleepQualityState(
            snackBarState = snackBarState,
            addSleepQualityViewModelState = addSleepQualityState,
            onAction = sleepQualityViewModel::onAction,
            onEvent = ::onEvent,
            onAddAction = addSleepQualityViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: AddSleepQualityState) = AddSleepQualityUI(state)
}
