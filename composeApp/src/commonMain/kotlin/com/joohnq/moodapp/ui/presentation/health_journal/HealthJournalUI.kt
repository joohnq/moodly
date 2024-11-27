package com.joohnq.moodapp.ui.presentation.health_journal

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
import com.joohnq.moodapp.ui.components.HealthJournalComponentColorful
import com.joohnq.moodapp.ui.components.SharedPanelComponent
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.health_journal.event.HealthJournalEvent
import com.joohnq.moodapp.ui.presentation.health_journal.state.HealthJournalState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.util.helper.DatetimeManager
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.health_journal
import moodapp.composeapp.generated.resources.journal_history
import moodapp.composeapp.generated.resources.journals_this_year
import org.jetbrains.compose.resources.stringResource

@Composable
fun HealthJournalUI(
    state: HealthJournalState
) {
    val dayPerYear = remember { DatetimeManager.getHealthJournalsInYear(state.healthJournal) }
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
                    healthJournals = state.healthJournal,
                    onClick = { state.onEvent(HealthJournalEvent.OnClick(it)) }
                )
            }
        }
    )
}