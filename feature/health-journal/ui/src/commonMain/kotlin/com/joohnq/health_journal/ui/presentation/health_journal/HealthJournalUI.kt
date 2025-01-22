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
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.health_journal.ui.components.HealthJournalComponentColorful
import com.joohnq.health_journal.ui.presentation.health_journal.event.HealthJournalEvent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SharedPanelComponent
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.health_journal
import com.joohnq.shared_resources.journal_history
import com.joohnq.shared_resources.journals_this_year
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.splash.ui.presentation.splash_screen.SplashScreenUI
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun HealthJournalUI(
    healthJournal: UiState<List<HealthJournalRecord>>,
    onEvent: (HealthJournalEvent) -> Unit = {},
) {
    healthJournal.foldComposable(
        onLoading = { SplashScreenUI() },
        onSuccess = { healthJournals: List<HealthJournalRecord> ->
            val getHealthJournalsInYearUseCase = koinInject<GetHealthJournalsInYearUseCase>()
            val dayPerYear =
                remember { getHealthJournalsInYearUseCase(healthJournals = healthJournals) }
            SharedPanelComponent(
                isDark = false,
                onGoBack = { onEvent(HealthJournalEvent.OnGoBack) },
                backgroundColor = Colors.Brown60,
                backgroundImage = Drawables.Images.HealthJournalBackground,
                panelTitle = Res.string.health_journal,
                bodyTitle = Res.string.journal_history,
                color = Colors.Brown50,
                onAdd = { onEvent(HealthJournalEvent.OnNavigateToAddHealthJournalScreen) },
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
                            onClick = { onEvent(HealthJournalEvent.OnClick(it)) }
                        )
                    }
                }
            )
        }
    )
}