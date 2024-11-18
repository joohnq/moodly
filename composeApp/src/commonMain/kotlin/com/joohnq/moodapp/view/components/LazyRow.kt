package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.view.screens.home.HomeAction
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalExtraExtraSmall
import com.joohnq.moodapp.view.ui.TextStyles
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.freud_score
import moodapp.composeapp.generated.resources.health_journal
import moodapp.composeapp.generated.resources.mood
import org.jetbrains.compose.resources.stringResource

@Composable
fun MentalHealthMetrics(
    freudScore: FreudScore,
    statsRecord: StatsRecord,
    healthJournal: Map<String, List<StatsRecord>?>,
    onAction: (HomeAction) -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        item {
            MentalHealthMetricItem(
                title = Res.string.freud_score,
                icon = Drawables.Icons.Heart,
                backgroundColor = freudScore.palette.backgroundColor,
                onClick = { onAction(HomeAction.OnNavigateToFreudScore) }
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
                backgroundColor = statsRecord.mood.palette.color,
                onClick = { onAction(HomeAction.OnNavigateToMood) }
            ) {
                MentalHealthMetricsMoodComponent(
                    it,
                    statsRecord.mood
                )
            }
        }
        item {
            MentalHealthMetricItem(
                title = Res.string.health_journal,
                icon = Drawables.Icons.Document,
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

@Composable
fun AddMoodRadioGroup(
    moodsSize: Int,
    moodIndex: Int,
    selectedMood: Mood,
    setSelectedMood: (Int) -> Unit
) {
    BoxWithConstraints {
        val dividerWidth = (maxWidth - 180.dp - 40.dp) / 4
        LazyRow(
            modifier = Modifier.wrapContentSize(Alignment.Center).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            items(moodsSize) { i ->
                Button(
                    modifier = Modifier.size(36.dp),
                    colors = ButtonColors(
                        containerColor = if (i <= moodIndex) Colors.White else selectedMood.palette.moodScreenInactiveColor,
                        contentColor = selectedMood.palette.moodScreenBackgroundColor,
                        disabledContainerColor = if (i <= moodIndex) Colors.White else selectedMood.palette.moodScreenInactiveColor,
                        disabledContentColor = selectedMood.palette.moodScreenBackgroundColor
                    ),
                    shape = Dimens.Shape.Circle,
                    onClick = {
                        setSelectedMood(i)
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier.size(8.dp).background(
                            color = selectedMood.palette.moodScreenBackgroundColor,
                            shape = Dimens.Shape.Circle
                        )
                    )
                }

                if (i < moodsSize - 1) {
                    Box(
                        modifier = Modifier.width(dividerWidth).height(10.dp)
                            .paddingHorizontalExtraExtraSmall()
                            .background(color = if (moodIndex - 1 >= i) Colors.White else selectedMood.palette.moodScreenInactiveColor)
                    )
                }
            }
        }
    }
}