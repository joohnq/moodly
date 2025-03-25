package com.joohnq.self_journal.ui.presentation.add_self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.mood.ui.mapper.toDomain
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.ui.presentation.add_self_journal.event.AddSelfJournalEvent
import com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel.AddJournalingViewModel
import com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel.AddSelfJournalIntent
import com.joohnq.self_journal.ui.viewmodel.SelfJournalIntent
import com.joohnq.self_journal.ui.viewmodel.SelfJournalSideEffect
import com.joohnq.self_journal.ui.viewmodel.SelfJournalViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSelfJournalScreen(onGoBack: () -> Unit) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val addJournalingViewModel: AddJournalingViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val state by addJournalingViewModel.state.collectAsState()

    fun onError(error: String) =
        scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: AddSelfJournalEvent) =
        when (event) {
            AddSelfJournalEvent.OnGoBack -> onGoBack()
            AddSelfJournalEvent.OnAdd ->
                selfJournalViewModel.onAction(
                    SelfJournalIntent.Add(
                        SelfJournalRecord(
                            mood = state.mood!!.toDomain(),
                            title = state.title,
                            description = state.description
                        )
                    )
                )
        }

    LaunchedEffect(selfJournalViewModel) {
        selfJournalViewModel.sideEffect.collect { effect ->
            when (effect) {
                SelfJournalSideEffect.SelfJournalAdded -> {
                    onEvent(AddSelfJournalEvent.OnGoBack)
                    selfJournalViewModel.onAction(SelfJournalIntent.GetAll)
                }

                is SelfJournalSideEffect.ShowError -> onError(effect.error.message.toString())
                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addJournalingViewModel.onAction(AddSelfJournalIntent.ResetState)
        }
    }

    return AddJournalingUI(
        snackBarState = snackBarState,
        state = state,
        onAction = addJournalingViewModel::onAction,
        onEvent = ::onEvent
    )
}
