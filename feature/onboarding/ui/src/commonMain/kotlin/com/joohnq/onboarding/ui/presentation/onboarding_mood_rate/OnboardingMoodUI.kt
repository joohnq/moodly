package com.joohnq.onboarding.ui.presentation.onboarding_mood_rate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.components.RouletteMoods
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.viewmodel.OnboardingContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.IconContinueButton
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.mood_rate_desc
import com.joohnq.shared_resources.mood_rate_title
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OnboardingMoodRateUI(
    record: MoodRecordResource,
    onEvent: (OnboardingContract.Event) -> Unit = {},
    onIntent: (OnboardingContract.Intent) -> Unit = {},
) {
    OnboardingBaseComponent(
        page = 1,
        title = Res.string.mood_rate_title,
        isContinueButtonVisible = false,
        onGoBack = { onEvent(OnboardingContract.Event.GoBack) },
    ) {
        Text(
            text = stringResource(
                Res.string.mood_rate_desc,
                stringResource(record.mood.text)
            ),
            style = TextStyles.textXlSemiBold(),
            color = Colors.Brown100Alpha64,
        )
        VerticalSpacer(24.dp)
        MoodFace(
            modifier = Modifier.size(120.dp),
            resource = record.mood,
        )
        VerticalSpacer(24.dp)
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(all = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconContinueButton(
            modifier = Modifier.size(60.dp),
            colors = ComponentColors.IconButton.ContinueButtonColors(),
            onClick = { onEvent(OnboardingContract.Event.OnContinue) }
        )
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val carouselOffset = when {
            maxWidth >= 400.dp -> maxHeight - 200.dp
            else -> maxHeight - (maxWidth / 2) + 50.dp
        }

        Box(
            modifier = Modifier
                .sizeIn(maxWidth = 400.dp, maxHeight = 400.dp)
                .offset(y = carouselOffset),
            contentAlignment = Alignment.TopCenter
        ) {
            RouletteMoods(
                modifier = Modifier.fillMaxSize().scale(1.4f),
                setSelectedMood = { onIntent(OnboardingContract.Intent.UpdateMood(it)) }
            )
        }
    }
}

@Preview
@Composable
fun OnboardingMoodRateUIPreview() {
    OnboardingMoodRateUI(
        record = MoodRecordResource(),
    )
}
