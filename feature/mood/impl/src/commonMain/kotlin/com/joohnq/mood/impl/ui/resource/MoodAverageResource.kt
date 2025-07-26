package com.joohnq.mood.impl.ui.resource

import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.negative
import com.joohnq.shared_resources.neutral
import com.joohnq.shared_resources.positive
import com.joohnq.shared_resources.skipped
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.resources.StringResource

sealed class MoodAverageResource(
    val backgroundColor: Color,
    val color: Color,
    val text: StringResource
) {
    data object Positive : MoodAverageResource(
        backgroundColor = Colors.Green40,
        color = Colors.Green80,
        text = Res.string.positive
    )

    data object Neutral : MoodAverageResource(
        backgroundColor = Colors.Gray40,
        color = Colors.Gray80,
        text = Res.string.neutral
    )

    data object Negative : MoodAverageResource(
        backgroundColor = Colors.Pink40,
        color = Colors.Pink80,
        text = Res.string.negative
    )

    data object Skipped : MoodAverageResource(
        backgroundColor = Colors.White,
        color = Colors.White,
        text = Res.string.skipped
    )
}
