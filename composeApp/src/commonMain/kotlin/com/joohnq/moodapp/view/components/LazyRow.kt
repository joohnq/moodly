package com.joohnq.moodapp.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.screens.home.action.HomeAction
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.freud_score
import moodapp.composeapp.generated.resources.health_jornal
import moodapp.composeapp.generated.resources.mood
import org.jetbrains.compose.resources.stringResource

@Composable
fun MentalHealthMetrics(
    freudScore: FreudScore,
    mood: Mood?,
    healthJournal: List<StatsRecord?>,
    onAction: (HomeAction) -> Unit,
) {
    val freudScorePalette = FreudScore.getPalette(freudScore)
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        item {
            MentalHealthMetricItem(
                title = Res.string.freud_score,
                icon = Drawables.Icons.Heart,
                backgroundColor = freudScorePalette.backgroundColor,
                onClick = { onAction(HomeAction.OnNavigateToFreudScore) }
            ) {
                CircularProgressWithText(
                    modifier = it.size(130.dp),
                    color = freudScorePalette.color,
                    backgroundColor = freudScorePalette.subColor,
                    progress = { freudScore.score.toFloat() / 100 },
                ) {
                    Text(text = freudScore.score.toString(), style = TextStyles.FreudScore())
                    Text(text = stringResource(freudScore.title), style = TextStyles.FreudTitle())
                }
            }
        }
        item {
            val moodPalette = Mood.getPalette(mood!!)
            MentalHealthMetricItem(
                title = Res.string.mood,
                icon = Drawables.Icons.SadFace,
                backgroundColor = moodPalette.faceColor,
                onClick = { onAction(HomeAction.OnNavigateToMood) }
            ) {
                MoodComponent(
                    it,
                    mood
                )
            }
        }
        item {
            MentalHealthMetricItem(
                title = Res.string.health_jornal,
                icon = Drawables.Icons.DocumentHealth,
                backgroundColor = Colors.Purple30,
                onClick = { onAction(HomeAction.OnNavigateToHealthJournal) }
            ) {
                HealthJournalComponent(
                    it,
                    healthJournal
                )
            }
        }
    }
}