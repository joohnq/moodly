package com.joohnq.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.freud_score.ui.FreudScoreResource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.components.HealthJournalComponent
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.MoodResource.Companion.toResource
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.components.CircularProgressWithDoubleText
import com.joohnq.shared.ui.components.MentalHealthMetricItem
import com.joohnq.shared.ui.freud_score
import com.joohnq.shared.ui.health_journal
import com.joohnq.shared.ui.mood
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun MentalHealthMetrics(
    freudScore: FreudScoreResource?,
    statsRecord: StatsRecord,
    healthJournal: List<HealthJournalRecord>,
    onEvent: (HomeEvent) -> Unit,
) {
    val resource = statsRecord.mood.toResource()
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        item {
            if (freudScore == null)
                CircularProgressIndicator()
            else
                MentalHealthMetricItem(
                    title = Res.string.freud_score,
                    icon = Drawables.Icons.Heart,
                    backgroundColor = freudScore.palette.backgroundColor,
                    onClick = { onEvent(HomeEvent.OnNavigateToFreudScore) }
                ) {
                    CircularProgressWithDoubleText(
                        modifier = it.size(130.dp),
                        color = freudScore.palette.color,
                        backgroundColor = freudScore.palette.subColor,
                        progress = { freudScore.score.toFloat() / 100 },
                        firstText = freudScore.score.toString(),
                        firstTextStyle = TextStyles.Text2xlExtraBold(),
                        secondText = stringResource(freudScore.title),
                        secondTextStyle = TextStyles.TextSmSemiBold(),
                        textColor = freudScore.palette.color
                    )
                }
        }
        item {
            MentalHealthMetricItem(
                title = Res.string.mood,
                icon = Drawables.Icons.SadFace,
                backgroundColor = resource.palette.color,
                onClick = { onEvent(HomeEvent.OnNavigateToMood) }
            ) { modifier ->
                MentalHealthMetricsMoodComponent(
                    modifier,
                    statsRecord.mood.toResource()
                )
            }
        }
        item {
            MentalHealthMetricItem(
                title = Res.string.health_journal,
                icon = Drawables.Icons.Document,
                backgroundColor = Colors.Purple30,
                onClick = { onEvent(HomeEvent.OnNavigateToHealthJournal) }
            ) { modifier ->
                HealthJournalComponent(
                    modifier,
                    healthJournal
                )
            }
        }
    }
}
