package com.joohnq.mood.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.PreviousNextButton
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.your_mood_is
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodPanel(
    modifier: Modifier = Modifier,
    resource: MoodResource,
    hasPrevious: Boolean,
    hasNext: Boolean,
    onEvent: (MoodEvent) -> Unit
) {
    Column(
        modifier = modifier
            .paddingHorizontalMedium()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(Res.string.your_mood_is),
            style = TextStyles.TextXlBold(),
            color = Colors.White
        )
        Text(
            text = stringResource(resource.text),
            style = TextStyles.Heading2xlExtraBold(),
            color = Colors.White
        )
        VerticalSpacer(10.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PreviousNextButton(
                enabled = hasPrevious,
                isPrevious = true,
                onClick = { onEvent(MoodEvent.OnPrevious) },
                color = resource.palette.color
            )
            MoodFace(
                modifier = Modifier.size(96.dp),
                resource = resource,
                backgroundColor = Colors.White,
                color = resource.palette.color
            )
            PreviousNextButton(
                enabled = hasNext,
                isPrevious = false,
                onClick = { onEvent(MoodEvent.OnNext) },
                color = resource.palette.color
            )
        }
    }
}
