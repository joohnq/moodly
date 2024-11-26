package com.joohnq.moodapp.ui.presentation.all_journals

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.ui.components.TopBar
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.moodapp.ui.presentation.all_journals.state.AllJournalState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import kotlinx.datetime.LocalDate
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.my_journals
import moodapp.composeapp.generated.resources.timeline
import org.jetbrains.compose.resources.stringResource

@Composable
fun AllJournalUI(state: AllJournalState) {
    val keys = state.healthJournals.keys.toList()
    val key = state.healthJournals.keys.find { it == state.selectedDateTime }
        ?: state.healthJournals.keys.last()
    val list = state.healthJournals[key]
    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.background(
                    color = Colors.Brown80,
                    shape = Dimens.Shape.BottomMedium
                ).padding(top = padding.calculateTopPadding(), bottom = 30.dp)
            ) {
                TopBar(
                    modifier = Modifier.paddingHorizontalMedium(),
                    isDark = false,
                    onGoBack = { state.onEvent(AllJournalEvent.OnGoBack) },
                )
                VerticalSpacer(10.dp)
                Text(
                    text = stringResource(Res.string.my_journals),
                    style = TextStyles.HeadingSmExtraBold(),
                    color = Colors.White,
                    modifier = Modifier.paddingHorizontalMedium()
                )
                VerticalSpacer(10.dp)
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    state = rememberLazyListState(initialFirstVisibleItemIndex = keys.lastIndex)
                ) {
                    items(keys) {
                        val isSelected = it == state.selectedDateTime
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.background(
                                color = if (isSelected) Colors.White else Colors.Brown70,
                                shape = Dimens.Shape.Circle
                            ).padding(horizontal = 10.dp, vertical = 15.dp)
                                .clickable { state.onEvent(AllJournalEvent.OnSelectDate(it)) }
//                                .then(
//                                    if (isSelected) Modifier.border(
//                                        3.dp,
//                                        color = Colors.Brown100Alpha64,
//                                        shape = Dimens.Shape.Circle
//                                    ) else Modifier
//                                )
                        ) {
                            Text(
                                text = "daw",
                                style = TextStyles.TextXsBold(),
                                color = if (isSelected) Colors.Brown80 else Colors.Brown20
                            )
                            VerticalSpacer(5.dp)
                            Text(
                                text = it.dayOfMonth.toString(),
                                style = TextStyles.TextMdExtraBold(),
                                color = if (isSelected) Colors.Brown80 else Colors.Brown20
                            )
                        }
                    }
                }
            }
            VerticalSpacer(20.dp)
            Text(
                text = stringResource(Res.string.timeline),
                style = TextStyles.TextLgExtraBold(),
                color = Colors.Brown80,
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium()
            )
            VerticalSpacer(20.dp)
            if (list == null) {
                Box(
                    modifier = Modifier.height(250.dp).fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Empty",
                        style = TextStyles.Text2xlExtraBold(),
                        color = Colors.Brown100Alpha64,
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(list) { i, healthJournal ->
                        AllJournalsCard(
                            i = i,
                            healthJournal = healthJournal,
                            lastIndex = list.lastIndex,
                            onEvent = state.onEvent,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    AllJournalUI(
        AllJournalState(
            healthJournals = mapOf(
                LocalDate(2022, 1, 1) to listOf(
                    HealthJournalRecord.init()
                        .copy(
                            title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            mood = Mood.Overjoyed
                        ),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Happy),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Neutral),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Sad),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Depressed),
                ),
                LocalDate(2022, 1, 2) to listOf(
                    HealthJournalRecord.init()
                        .copy(
                            title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            mood = Mood.Overjoyed
                        ),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Happy),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Neutral),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Sad),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Depressed),
                ),
                LocalDate(2022, 1, 3) to listOf(
                    HealthJournalRecord.init()
                        .copy(
                            title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            mood = Mood.Overjoyed
                        ),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Happy),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Neutral),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Sad),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Depressed),
                ),
                LocalDate(2022, 1, 4) to listOf(
                    HealthJournalRecord.init()
                        .copy(
                            title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            mood = Mood.Overjoyed
                        ),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Happy),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Neutral),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Sad),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Depressed),
                ),
            ),
            onEvent = {},
            selectedDateTime = LocalDate(2022, 1, 1)
        )
    )
}

@Preview
@Composable
fun Preview2() {
    AllJournalUI(
        AllJournalState(
            healthJournals = mapOf(
                LocalDate(2022, 1, 1) to listOf(
                    HealthJournalRecord.init()
                        .copy(
                            title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            mood = Mood.Overjoyed
                        ),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Happy),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Neutral),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Sad),
                    HealthJournalRecord.init()
                        .copy(title = "Title", description = "Description", mood = Mood.Depressed),
                ),
            ),
            onEvent = {},
            selectedDateTime = LocalDate(2022, 1, 1)
        )
    )
}