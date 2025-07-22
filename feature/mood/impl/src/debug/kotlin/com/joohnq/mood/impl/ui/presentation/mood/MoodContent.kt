package com.joohnq.mood.impl.ui.presentation.mood

import androidx.compose.runtime.Composable
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.foldComposable
import com.joohnq.mood.impl.ui.components.MoodContent
import com.joohnq.mood.impl.ui.components.MoodPanel
import com.joohnq.mood.impl.ui.fake.depressedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.happyMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.neutralMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.overjoyedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.sadMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.mapper.getTodayMoodRecord
import com.joohnq.mood.impl.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.mood.impl.ui.viewmodel.MoodIntent
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodContentEmptyPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                MoodRecordResource(
                    createdAt = LocalDateTime(2025, 1, 1, 0, 0, 0)
                )
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentLoadingPreview() {
    MoodContent(
        records = UiState.Loading
    )
}

@Preview
@Composable
fun MoodContentErrorPreview() {
    MoodContent(
        records = UiState.Error("Something went wrong")
    )
}

@Preview
@Composable
fun MoodContentDepressedPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                depressedMoodRecordResourcePreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentSadPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                sadMoodRecordResourcePreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentNeutralPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                neutralMoodRecordResourcePreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentHappyPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                happyMoodRecordResourcePreview
            )
        ),
    )
}

@Preview
@Composable
fun MoodContentOverjoyedPreview() {
    MoodContent(
        records = UiState.Success(
            listOf(
                overjoyedMoodRecordResourcePreview
            )
        ),
    )
}
