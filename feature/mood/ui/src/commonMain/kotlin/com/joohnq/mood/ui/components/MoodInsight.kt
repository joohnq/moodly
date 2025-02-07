package com.joohnq.mood.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.lets_log_your_first_mood_to_see_your_mood_insights_improve_your_mental_health_now
import com.joohnq.shared_resources.log_first_mood
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun MoodInsight(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.White,
    records: List<MoodRecord>,
    onClick: () -> Unit
) {
    if (records.isEmpty())
        NotFoundHorizontal(
            modifier = modifier,
            containerColor = containerColor,
            title = Res.string.lets_log_your_first_mood_to_see_your_mood_insights_improve_your_mental_health_now,
            subtitle = Res.string.log_first_mood,
            image = Drawables.Images.MoodInsight,
            onClick = onClick
        )
    else
        MoodInsightContent(
            modifier = modifier,
            containerColor = containerColor,
            records = records,
            onClick = onClick
        )
}