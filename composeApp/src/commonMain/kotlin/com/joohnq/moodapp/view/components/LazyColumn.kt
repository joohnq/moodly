package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mindful_journal
import moodapp.composeapp.generated.resources.mood_tracker
import moodapp.composeapp.generated.resources.stress_level
import org.jetbrains.compose.resources.stringResource

@Composable
fun MindfulTracker(sleepQuality: SleepQuality, stressLevel: StressLevel, moodTracker: List<Mood>) {
    val sleepQualityPalette = SleepQuality.getPalette(sleepQuality)

    MindfulTrackerCardRow(
        icon = Drawables.Icons.HospitalBed,
        color = sleepQualityPalette.color,
        backgroundColor = sleepQualityPalette.backgroundColor,
        title = sleepQuality.firstText,
        subtitle = sleepQuality.secondText,
        content = {
            CircularProgressWithText(
                modifier = Modifier.size(56.dp),
                color = sleepQualityPalette.color,
                backgroundColor = sleepQualityPalette.backgroundColor,
                progress = { sleepQuality.level * 0.2f },
            ) {
                Text(
                    text = sleepQuality.level.toString(),
                    style = TextStyles.SleepQualityOption()
                )
            }
        }
    ) {

    }
    Spacer(modifier = Modifier.height(16.dp))
    MindfulTrackerCardRow(
        icon = Drawables.Icons.DocumentHealth,
        color = Colors.Orange40,
        backgroundColor = Colors.Orange10,
        title = Res.string.mindful_journal,
        subtitle = Res.string.mindful_journal,
        content = { modifier ->
            LazyVerticalGrid(
                modifier = modifier.wrapContentSize(unbounded = true).size(60.dp),
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(16) { i ->
                    val background = when (i) {
                        5, 6, 7, 8, 9 -> Colors.Orange40
                        2, 3, 10, 11 -> Colors.Orange20
                        else -> Colors.Orange10
                    }

                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                color = background,
                                shape = RoundedCornerShape(4.dp)
                            ),
                    )
                }
            }
        }
    ) {

    }
    Spacer(modifier = Modifier.height(16.dp))
    MindfulTrackerCardColumn(
        icon = Drawables.Icons.Head,
        color = StressLevel.getBushColor(stressLevel.level),
        backgroundColor = StressLevel.getBushBackgroundColor(stressLevel.level),
        title = Res.string.stress_level,
        subtitle = stressLevel.subtitle,
        content = {
            BoxWithConstraints {
                val boxWidth = (maxWidth - 16.dp) / 5
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    for (i in 1..5) {
                        val isSelected = i <= stressLevel.level
                        val background = if (isSelected) Modifier.background(
                            brush = Brush.linearGradient(StressLevel.getBrushes(i)),
                            shape = CircleShape
                        )
                        else Modifier.background(
                            color = Colors.Brown20,
                            shape = CircleShape
                        )
                        Box(
                            modifier = Modifier.width(boxWidth).height(6.dp)
                                .then(background)
                        )
                    }
                }
            }
        }
    ) {

    }
    Spacer(modifier = Modifier.height(30.dp))
    MindfulTrackerCardColumn(
        icon = Drawables.Icons.HappyFace,
        color = Colors.Brown80,
        backgroundColor = Colors.Brown10,
        title = Res.string.mood_tracker,
        content = {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (mood in moodTracker) {
                    val moodPalette = Mood.getPalette(mood)
                    Box(
                        modifier = Modifier.background(
                            color = moodPalette.color,
                            shape = CircleShape
                        ).padding(vertical = 8.dp, horizontal = 12.dp),
                    ) {
                        Text(
                            text = stringResource(mood.text),
                            style = TextStyles.MindfulTrackerCardMood().copy(
                                color = moodPalette.backgroundColor
                            )
                        )
                    }
                }
            }
        }
    ) {

    }

    Spacer(modifier = Modifier.height(30.dp))
}