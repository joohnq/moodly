package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.lets_log_your_first_mood_to_see_your_mood_insights_improve_your_mental_health_now
import com.joohnq.shared_resources.log_first_mood
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun MoodInsight(
    modifier: Modifier = Modifier,
    records: List<MoodRecordResource>,
    onCreate: () -> Unit = {},
) {
    if (records.isEmpty()) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.lets_log_your_first_mood_to_see_your_mood_insights_improve_your_mental_health_now,
            subtitle = Res.string.log_first_mood,
            image = Drawables.Images.MoodInsight,
            onClick = onCreate
        )
    } else {
        MoodInsightContent(
            modifier = modifier,
            records = records
        )
    }
}
