package com.joohnq.moodapp.view.screens.journaling

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.entities.HealthJournalRecord
import com.joohnq.moodapp.helper.DatetimeManager
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.HealthJournalCard
import com.joohnq.moodapp.view.components.HealthJournalStatsCard
import com.joohnq.moodapp.view.components.Title
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToEditJournalingScreen
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.view.state.UiState.Companion.onSuccessComposable
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.all_journals
import moodapp.composeapp.generated.resources.completed
import moodapp.composeapp.generated.resources.document_your_mental_journal
import moodapp.composeapp.generated.resources.emotion
import moodapp.composeapp.generated.resources.journal_stats
import moodapp.composeapp.generated.resources.no_data
import moodapp.composeapp.generated.resources.your_entries
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JournalingScreenUI(
    padding: PaddingValues = PaddingValues(0.dp),
    journals: List<HealthJournalRecord>,
    onClick: (Int) -> Unit = {}
) {
    Scaffold(
        containerColor = Colors.Brown10,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            VerticalSpacer(20.dp)
            Column(modifier = Modifier.paddingHorizontalMedium()) {
                Text(
                    text = stringResource(Res.string.your_entries),
                    style = TextStyles.HeadingMdExtraBold(),
                    color = Colors.Brown80
                )
                Text(
                    text = stringResource(Res.string.document_your_mental_journal),
                    style = TextStyles.ParagraphLg(),
                    color = Colors.Brown100Alpha64
                )
            }
            Title(text = Res.string.all_journals)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier.heightIn(min = 250.dp)
            ) {
                if (journals.isEmpty()) {
                    item {
                        Text(text = "Empty")
                    }
                } else {
                    items(journals) { journal ->
                        HealthJournalCard(journal = journal, onClick = { onClick(journal.id) })
                    }
                }
            }
            Title(text = Res.string.journal_stats)
            FlowRow(
                maxItemsInEachRow = 2,
                maxLines = 1,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.paddingHorizontalMedium()
            ) {
                HealthJournalStatsCard(
                    modifier = Modifier.weight(1f),
                    icon = Drawables.Icons.Document,
                    title = DatetimeManager.getHealthJournalsInYear(journals),
                    color = Colors.Green50,
                    backgroundColor = Colors.Green10,
                    desc = stringResource(Res.string.completed)
                )
                HealthJournalStatsCard(
                    modifier = Modifier.weight(1f),
                    icon = Drawables.Icons.Chart,
                    title = stringResource(if (journals.isEmpty()) Res.string.no_data else journals.first().mood.text),
                    color = Colors.Brown60,
                    backgroundColor = Colors.Brown10,
                    desc = stringResource(Res.string.emotion)
                )
            }
        }
    }
}


@Composable
fun JournalingScreen(
    padding: PaddingValues = PaddingValues(0.dp),
    navigation: NavHostController,
    healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
) {
    val journal by healthJournalViewModel.healthJournalState.collectAsState()

    journal.healthJournalRecords.onSuccessComposable {
        JournalingScreenUI(
            padding = padding,
            journals = journal.healthJournalRecords.getValue(),
            onClick = navigation::onNavigateToEditJournalingScreen
        )
    }
}

@Preview
@Composable
fun JournalingScreenPreview() {
    JournalingScreenUI(
        journals = listOf(HealthJournalRecord.init(), HealthJournalRecord.init())
    )
}

@Preview
@Composable
fun JournalingScreenPreview2() {
    JournalingScreenUI(
        journals = listOf()
    )
}