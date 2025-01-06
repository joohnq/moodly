package com.joohnq.health_journal.ui.presentation.health_journal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.presentation.loading.LoadingUI
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.health_journal.ui.components.HealthJournalComponentColorful
import com.joohnq.health_journal.ui.presentation.health_journal.event.HealthJournalEvent
import com.joohnq.health_journal.ui.presentation.health_journal.state.HealthJournalState
import com.joohnq.shared.domain.mapper.foldComposable
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.components.SharedPanelComponent
import com.joohnq.shared.ui.components.VerticalSpacer
import com.joohnq.shared.ui.health_journal
import com.joohnq.shared.ui.journal_history
import com.joohnq.shared.ui.journals_this_year
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared.ui.theme.TextStyles
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun HealthJournalUI(
    state: HealthJournalState,
) {
    state.healthJournal.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { healthJournals: List<HealthJournalRecord> ->
            val getHealthJournalsInYearUseCase = koinInject<GetHealthJournalsInYearUseCase>()
            val dayPerYear =
                remember { getHealthJournalsInYearUseCase(healthJournals) }
            SharedPanelComponent(
                isDark = false,
                onGoBack = { state.onEvent(HealthJournalEvent.OnGoBack) },
                backgroundColor = Colors.Brown60,
                backgroundImage = Drawables.Images.HealthJournalBackground,
                panelTitle = Res.string.health_journal,
                bodyTitle = Res.string.journal_history,
                color = Colors.Brown50,
                onAdd = { state.onEvent(HealthJournalEvent.OnNavigateToAddHealthJournalScreen) },
                panelContent = {
                    Column(
                        modifier = Modifier.paddingHorizontalMedium()
                            .padding(top = it.calculateTopPadding()).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = dayPerYear,
                            style = TextStyles.Heading2xlExtraBold(),
                            color = Colors.White
                        )
                        VerticalSpacer(10.dp)
                        Text(
                            text = stringResource(Res.string.journals_this_year),
                            style = TextStyles.TextXlSemiBold(),
                            color = Colors.White
                        )
                    }
                },
                content = {
                    item {
                        VerticalSpacer(10.dp)
                        HealthJournalComponentColorful(
                            healthJournals = healthJournals,
                            onClick = { state.onEvent(HealthJournalEvent.OnClick(it)) }
                        )
                    }
                }
            )
        }
    )
}