package com.joohnq.self_journal.ui.presentation.journaling

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.self_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.self_journal.ui.viewmodel.SelfJournalViewModel

@Composable
fun JournalingScreen(
    padding: PaddingValues,
    onEvent: (JournalingEvent) -> Unit,
) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val journal by selfJournalViewModel.state.collectAsState()

    JournalingUI(
        padding = padding,
        records = journal.records,
        onEvent = onEvent
    )
}
