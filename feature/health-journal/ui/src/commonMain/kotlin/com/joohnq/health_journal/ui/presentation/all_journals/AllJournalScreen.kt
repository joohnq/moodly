package com.joohnq.health_journal.ui.presentation.all_journals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.health_journal.domain.use_case.OrganizeFromCreationHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalIntent
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModel
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import kotlinx.datetime.LocalDate
import org.koin.compose.koinInject

@Composable
fun AllJournalScreen(
    localDate: LocalDate? = null,
    onNavigateEditJournaling: (Int) -> Unit,
    onGoBack: () -> Unit,
) {
    val userViewModel: UserViewModel = sharedViewModel()
    val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
    val healthJournalState by healthJournalViewModel.state.collectAsState()
    val userState by userViewModel.state.collectAsState()
    val allJournalViewModel: AllJournalViewModel = sharedViewModel()
    val allJournalState by allJournalViewModel.state.collectAsState()
    val organizeFromCreationHealthJournalFreudScoreUseCase: OrganizeFromCreationHealthJournalFreudScoreUseCase =
        koinInject()

    fun onEvent(event: AllJournalEvent) =
        when (event) {
            AllJournalEvent.OnGoBack -> onGoBack()
            is AllJournalEvent.OnSelectJournal -> onNavigateEditJournaling(event.id)
            AllJournalEvent.OnDelete -> healthJournalViewModel.onAction(
                HealthJournalIntent.DeleteHealthJournal(
                    allJournalState.currentDeleteId
                )
            )
        }

    localDate?.let {
        LaunchedEffect(Unit) {
            allJournalViewModel.onAction(AllJournalIntent.UpdateSelectedDateTime(it))
        }
    }

    AllJournalUI(
        state = allJournalState,
        user = userState.user,
        organizeFromCreationHealthJournalFreudScoreUseCase = organizeFromCreationHealthJournalFreudScoreUseCase,
        onAction = allJournalViewModel::onAction,
        records = healthJournalState.healthJournalRecords,
        onEvent = ::onEvent,
    )
}

