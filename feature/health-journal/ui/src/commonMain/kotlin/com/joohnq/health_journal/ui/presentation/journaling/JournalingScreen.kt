package com.joohnq.health_journal.ui.presentation.journaling

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.health_journal.ui.presentation.journaling.state.JournalingState
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.shared.ui.CustomTab
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.journaling
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.shared.ui.state.UiState.Companion.getValue
import com.joohnq.shared.ui.theme.Drawables
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

class JournalingScreen : CustomTab<JournalingState>() {
    @Composable
    override fun Screen(): JournalingState {
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val journal by healthJournalViewModel.state.collectAsState()

        fun onEvent(event: JournalingEvent) =
            when (event) {
                is JournalingEvent.OnNavigateToEditJournalingScreen -> {}
//                    onNavigate(EditJournalingScreen(event.id), false)

                JournalingEvent.OnNavigateToAllJournals -> {}
//                    onNavigate(AllJournalScreen())
            }

        return JournalingState(
            journals = journal.healthJournalRecords.getValue(),
            onEvent = ::onEvent
        )
    }

    override val options: TabOptions
        @Composable
        get() =
            TabOptions(
                icon = painterResource(Drawables.Icons.Chat),
                title = stringResource(Res.string.journaling),
                index = 1u
            )


    @Composable
    override fun UI(state: JournalingState) = JournalingUI(state)
}
