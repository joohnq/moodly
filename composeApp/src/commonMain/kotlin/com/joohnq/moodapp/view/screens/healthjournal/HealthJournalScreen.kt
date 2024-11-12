package com.joohnq.moodapp.view.screens.healthjournal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.HealthJournalComponentColorful
import com.joohnq.moodapp.view.components.SharedItem
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.StatsViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.health_journal
import moodapp.composeapp.generated.resources.journal_history
import moodapp.composeapp.generated.resources.journals_this_year
import org.jetbrains.compose.resources.stringResource

@Composable
fun HealthJournalScreenUI(
    healthJournal: Map<String, List<StatsRecord>?>,
    onGoBack: () -> Unit = {},
    onAdd: () -> Unit = {},
) {
    val dayPerYear = remember { "2" }
    SharedItem(
        isDark = false,
        onGoBack = onGoBack,
        backgroundColor = Colors.Brown60,
        backgroundImage = Drawables.Images.HealthJournalBackground,
        panelTitle = Res.string.health_journal,
        bodyTitle = Res.string.journal_history,
        color = Colors.Brown50,
        onAdd = onAdd,
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
                HealthJournalComponentColorful(
                    modifier = Modifier,
                    healthJournal = healthJournal
                )
            }
        }
    )
}

@Composable
fun HealthJournalScreen(
    statsViewModel: StatsViewModel = sharedViewModel(),
    navigation: NavController
) {
    val healthJournal by statsViewModel.healthJournal.collectAsState()
    HealthJournalScreenUI(
        healthJournal = healthJournal,
        onGoBack = navigation::popBackStack,
        onAdd = {}
    )
}
