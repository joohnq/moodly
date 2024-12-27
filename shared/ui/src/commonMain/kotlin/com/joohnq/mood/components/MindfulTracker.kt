package com.joohnq.mood.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.domain.entity.Mood
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.Drawables
import com.joohnq.mood.theme.TextStyles
import com.joohnq.mood.ui.presentation.home.event.HomeEvent
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.mood_tracker
import com.joohnq.shared.ui.stress_level
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.stress_level.domain.entity.StressLevel

@Composable fun MindfulTracker(
    sleepQuality: SleepQuality,
    stressLevel: StressLevel,
    moodTracker: List<Mood>,
    onAction: (HomeEvent) -> Unit
) {
    MindfulTrackerCardRow(icon = Drawables.Icons.HospitalBed,
        color = sleepQuality.palette.color,
        backgroundColor = sleepQuality.palette.secondaryBackgroundColor,
        title = sleepQuality.firstText,
        subtitle = sleepQuality.secondText,
        content = {
            CircularProgressWithText(
                modifier = Modifier.size(56.dp),
                color = sleepQuality.palette.color,
                backgroundColor = sleepQuality.palette.secondaryBackgroundColor,
                progress = { sleepQuality.level * 0.2f },
                text = sleepQuality.level.toString(),
                textStyle = TextStyles.TextXsExtraBold(),
                textColor = Colors.Brown80
            )
        },
        onClick = { onAction(HomeEvent.OnNavigateToSleepQuality) }
    )
//    VerticalSpacer(16.dp)
//    MindfulTrackerCardRow(icon = Drawables.Icons.DocumentHealth,
//        color = Colors.Orange40,
//        backgroundColor = Colors.Orange10,
//        title = Res.string.mindful_journal,
//        subtitle = Res.string.mindful_journal,
//        content = { modifier ->
//            LazyVerticalGrid(
//                modifier = modifier.wrapContentSize(unbounded = true).size(60.dp),
//                columns = GridCells.Fixed(4),
//                horizontalArrangement = Arrangement.spacedBy(4.dp),
//                verticalArrangement = Arrangement.spacedBy(4.dp)
//            ) {
//                items(16, key = { it }) { i ->
//                    val background = when (i) {
//                        5, 6, 7, 8, 9 -> Colors.Orange40
//                        2, 3, 10, 11 -> Colors.Orange20
//                        else -> Colors.Orange10
//                    }
//
//                    Box(
//                        modifier = Modifier.size(12.dp).background(
//                            color = background, shape = Dimens.Shape.ExtraExtraSmall
//                        ),
//                    )
//                }
//            }
//        },
//        onClick = { onAction(HomeEvent.OnNavigateToMindfulJournal) }
//    )
    VerticalSpacer(16.dp)
    MindfulTrackerCardColumn(icon = Drawables.Icons.Head,
        color = stressLevel.palette.color,
        backgroundColor = stressLevel.palette.backgroundColor,
        title = Res.string.stress_level,
        subtitle = stressLevel.subtitle,
        content = {
            StressLevelIndicator(stressLevel)
        },
        onClick = { onAction(HomeEvent.OnNavigateToStressLevel) }
    )
    VerticalSpacer(16.dp)
    MindfulTrackerCardColumn(icon = Drawables.Icons.HappyFace,
        color = Colors.Brown80,
        backgroundColor = Colors.Brown10,
        title = Res.string.mood_tracker,
        content = {
            MoodTrackerRow(moodTracker)
        },
        onClick = { onAction(HomeEvent.OnNavigateToMood) }
    )
    VerticalSpacer(30.dp)
}