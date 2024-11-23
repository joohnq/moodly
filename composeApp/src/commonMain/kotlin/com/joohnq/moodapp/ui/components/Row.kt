package com.joohnq.moodapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalExtraExtraSmall
import com.joohnq.moodapp.ui.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodTrackerRow(moodTracker: List<Mood>) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        for (i in moodTracker.indices) {
            val mood = moodTracker[i]
            Box(
                modifier = Modifier.background(
                    color = mood.palette.backgroundColor, shape = Dimens.Shape.Circle
                ).padding(vertical = 6.dp, horizontal = 10.dp),
            ) {
                Text(
                    text = stringResource(mood.text),
                    style = TextStyles.LabelSm().copy(
                        color = mood.palette.color
                    )
                )
            }

            if (moodTracker.size - 1 != i) {
                Icon(
                    painter = painterResource(Drawables.Icons.Arrow),
                    contentDescription = null,
                    tint = Colors.Brown80,
                    modifier = Modifier.paddingHorizontalExtraExtraSmall().size(16.dp)
                )
            }
        }
    }
}
