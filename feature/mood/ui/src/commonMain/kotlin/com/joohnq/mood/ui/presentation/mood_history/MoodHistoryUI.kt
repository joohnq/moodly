package com.joohnq.mood.ui.presentation.mood_history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.mapper.foldComposable
import com.joohnq.mood.ui.components.MoodHistoryCard
import com.joohnq.mood.ui.presentation.mood_history.event.MoodHistoryEvent
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.SwipeTorRevealCard
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodHistoryUI(
    records: UiState<List<MoodRecordResource>>,
    onEvent: (MoodHistoryEvent) -> Unit = {},
) {
    records.foldComposable(
        onSuccess = { records ->
            Scaffold(
                containerColor = Colors.Brown10
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding).paddingHorizontalMedium()
                ) {
                    TopBar(
                        modifier = Modifier.fillMaxWidth(),
                        isDark = true,
                        onGoBack = { onEvent(MoodHistoryEvent.OnGoBack) }
                    )
                    VerticalSpacer(20.dp)
                    Text(
                        text = stringResource(Res.string.all_history),
                        style = TextStyles.TextLgBold(),
                        color = Colors.Gray80
                    )
                    VerticalSpacer(20.dp)
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(records) { record ->
                            SwipeTorRevealCard(
                                modifier = Modifier.fillMaxWidth(),
                                onAction = {},
                            ) { modifier ->
                                MoodHistoryCard(
                                    modifier = modifier,
                                    containerColor = Colors.White,
                                    record = record,
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun MoodHistoryUIPreviewEmpty() {
    MoodHistoryUI(
        records = UiState.Success(
            listOf(
                MoodRecordResource(
                    createdAt = LocalDateTime(2025, 1, 1, 0, 0, 0)
                )
            )
        ),
        onEvent = {},
    )
}

@Preview
@Composable
fun MoodHistoryUIPreviewDepressed() {
    MoodHistoryUI(
        records = UiState.Success(
            listOf(
                MoodRecordResource(
                    mood = MoodResource.Depressed,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}

@Preview
@Composable
fun MoodHistoryUIPreviewNeutral() {
    MoodHistoryUI(
        records = UiState.Success(
            listOf(
                MoodRecordResource(
                    mood = MoodResource.Neutral,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}

@Preview
@Composable
fun MoodHistoryUIPreviewHappy() {
    MoodHistoryUI(
        records = UiState.Success(
            listOf(
                MoodRecordResource(
                    mood = MoodResource.Happy,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}

@Preview
@Composable
fun MoodHistoryUIPreviewOverjoyed() {
    MoodHistoryUI(
        records = UiState.Success(
            listOf(
                MoodRecordResource(
                    mood = MoodResource.Overjoyed,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}
