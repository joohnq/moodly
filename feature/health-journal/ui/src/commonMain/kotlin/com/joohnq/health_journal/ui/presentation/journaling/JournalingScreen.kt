package com.joohnq.health_journal.ui.presentation.journaling

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import org.koin.compose.koinInject

@Composable
fun JournalingScreen(
    padding: PaddingValues,
    onNavigateToEditJournaling: (Int) -> Unit,
    onNavigateToAllJournals: () -> Unit,
) {
    val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
    val journal by healthJournalViewModel.state.collectAsState()
    val getHealthJournalsInYearUseCase = koinInject<GetHealthJournalsInYearUseCase>()

    fun onEvent(event: JournalingEvent) {
        when (event) {
            is JournalingEvent.OnNavigateToEditJournalingScreen ->
                onNavigateToEditJournaling(event.id)

            JournalingEvent.OnNavigateToAllJournals -> onNavigateToAllJournals()
        }
    }

    JournalingUI(
        padding = padding,
        getHealthJournalsInYearUseCase = getHealthJournalsInYearUseCase,
        records = journal.healthJournalRecords,
        onEvent = ::onEvent
    )
}
