package com.joohnq.home.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.mapper.getTodayStatRecord
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.set_up_mood
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.you_havent_set_up_any_mood_yet

@Composable
fun MoodMetric(
    records: List<StatsRecord>,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val today = records.getTodayStatRecord()
    val resource = today?.mood?.toResource()

    if (resource == null)
        NotFoundHorizontal(
            modifier = Modifier.paddingHorizontalMedium(),
            image = Drawables.Images.MoodIllustration,
            title = Res.string.you_havent_set_up_any_mood_yet,
            subtitle = Res.string.set_up_mood,
            onClick = onCreate
        )
    else {

    }

}