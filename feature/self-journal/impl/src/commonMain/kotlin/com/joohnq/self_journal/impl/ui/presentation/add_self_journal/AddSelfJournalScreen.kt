package com.joohnq.self_journal.impl.ui.presentation.add_self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.mood.impl.ui.mapper.toDomain
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalContract
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSelfJournalScreen(onGoBack: () -> Unit) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val addSelfJournalViewModel: AddSelfJournalViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val state by addSelfJournalViewModel.state.collectAsState()

    fun onError(error: String) =
        scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: AddSelfJournalContract.Event) =
        when (event) {
            AddSelfJournalContract.Event.OnGoBack -> onGoBack()
            AddSelfJournalContract.Event.OnAdd ->
                selfJournalViewModel.onAction(
                    SelfJournalContract.Intent.Add(
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
                SelfJournalContract.SideEffect.SelfJournalAdded -> {
                    onEvent(AddSelfJournalContract.Event.OnGoBack)
                    selfJournalViewModel.onAction(SelfJournalContract.Intent.GetAll)
                }

                is SelfJournalContract.SideEffect.ShowError -> onError(effect.error)
                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addSelfJournalViewModel.onAction(AddSelfJournalContract.Intent.ResetState)
        }
    }

    return AddJournalingContent(
        snackBarState = snackBarState,
        state = state,
        onAction = addSelfJournalViewModel::onAction,
        onEvent = ::onEvent
    )
}