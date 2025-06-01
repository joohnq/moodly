package com.joohnq.self_journal.ui.presentation.add_self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.mood.ui.resource.mapper.toDomain
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel.AddJournalContract
import com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel.AddJournalViewModel
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalContract
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSelfJournalScreen(onGoBack: () -> Unit) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val addJournalViewModel: AddJournalViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val state by addJournalViewModel.state.collectAsState()

    fun onError(error: String) =
        scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: AddJournalContract.Event) =
        when (event) {
            AddJournalContract.Event.GoBack -> onGoBack()
            AddJournalContract.Event.Add ->
                selfJournalViewModel.onIntent(
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
                    onEvent(AddJournalContract.Event.GoBack)
                    selfJournalViewModel.onIntent(SelfJournalContract.Intent.GetAll)
                }

                is SelfJournalContract.SideEffect.ShowError -> onError(effect.error.message.toString())
                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addJournalViewModel.onIntent(AddJournalContract.Intent.ResetState)
        }
    }

    return AddJournalingUI(
        snackBarState = snackBarState,
        state = state,
        onIntent = addJournalViewModel::onIntent,
        onEvent = ::onEvent
    )
}
