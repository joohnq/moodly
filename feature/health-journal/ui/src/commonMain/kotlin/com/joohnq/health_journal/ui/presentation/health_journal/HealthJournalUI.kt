package com.joohnq.health_journal.ui.presentation.health_journal

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
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
import com.joohnq.health_journal.ui.presentation.health_journal.event.toHealthJournalEvent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.SharedPanelComponent
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.health_journal
import com.joohnq.shared_resources.journals_this_year
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun HealthJournalUI(
    healthJournal: UiState<List<HealthJournalRecord>>,
    onEvent: (HealthJournalEvent) -> Unit = {},
) {
    healthJournal.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { healthJournals: List<HealthJournalRecord> ->
            val getHealthJournalsInYearUseCase = koinInject<GetHealthJournalsInYearUseCase>()
            val dayPerYear =
                remember { getHealthJournalsInYearUseCase(healthJournals = healthJournals) }

            SharedPanelComponent(
                isDark = false,
                paddingValues = Dimens.Padding.HorizontalMedium,
                backgroundColor = Colors.Brown60,
                image = Drawables.Images.MindfulnessJournalBackground,
                title = Res.string.health_journal,
                color = Colors.Brown70,
                onEvent = { event ->
                    onEvent(event.toHealthJournalEvent())
                },
                panel = {
                    Column(
                        modifier = Modifier.paddingHorizontalMedium().fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                12.dp,
                                alignment = Alignment.CenterHorizontally
                            )
                        ) {
                            Icon(
                                painter = painterResource(Drawables.Icons.Book),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = Colors.Brown20
                            )
                            Text(
                                text = dayPerYear,
                                style = TextStyles.Heading2xlExtraBold(),
                                color = Colors.Brown20
                            )
                        }
                        VerticalSpacer(10.dp)
                        Text(
                            text = stringResource(Res.string.journals_this_year),
                            style = TextStyles.TextXlSemiBold(),
                            color = Colors.Brown20
                        )
                    }
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