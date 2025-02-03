package com.joohnq.health_journal.ui.presentation.health_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.health_journal.ui.components.HealthJournalComponentColorful
import com.joohnq.health_journal.ui.components.HealthJournalPanel
import com.joohnq.health_journal.ui.presentation.health_journal.event.HealthJournalEvent
import com.joohnq.shared_resources.components.DecoratedConvexPanel
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.theme.Colors


@Composable
fun HealthJournalUI(
    healthJournal: UiState<List<HealthJournalRecord>>,
    getHealthJournalsInYearUseCase: GetHealthJournalsInYearUseCase,
    onEvent: (HealthJournalEvent) -> Unit = {},
) {
    healthJournal.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { healthJournals: List<HealthJournalRecord> ->
            val dayPerYear =
                remember { getHealthJournalsInYearUseCase(healthJournals = healthJournals) }

            DecoratedConvexPanel(
                panelBackgroundColor = Colors.Brown60,
                backgroundColor = Colors.White,
                panelContent = {
                    HealthJournalPanel(dayPerYear)
                },
                content = {
                    HealthJournalComponentColorful(
                        healthJournals = healthJournals,
                        onClick = { onEvent(HealthJournalEvent.OnClick(it)) }
                    )
                }
            )
        }
    )
}