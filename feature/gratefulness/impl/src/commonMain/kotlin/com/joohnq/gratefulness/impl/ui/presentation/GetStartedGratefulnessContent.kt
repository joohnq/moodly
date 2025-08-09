package com.joohnq.gratefulness.add.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.get_started
import com.joohnq.shared_resources.get_started_description
import com.joohnq.shared_resources.get_started_title
import com.joohnq.shared_resources.more_mindful
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.updated_daily
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GetStartedGratefulnessContent() {
    Scaffold(
        containerColor = Colors.Brown5
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .paddingHorizontalMedium(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(Drawables.Images.GratefulnessGetStarted),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            VerticalSpacer(32.dp)
            Text(
                text = stringResource(Res.string.get_started_title),
                style = TextStyles.headingSmBold(),
                color = Colors.Brown80,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(12.dp)
            Text(
                text = stringResource(Res.string.get_started_description),
                style = TextStyles.paragraphMdMedium(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(24.dp)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
            ) {
                GetStartedGratefulnessItem(
                    icon = Drawables.Icons.Outlined.Schedule,
                    text = Res.string.updated_daily
                )

                GetStartedGratefulnessItem(
                    icon = Drawables.Icons.Outlined.SelfImprovement,
                    text = Res.string.more_mindful
                )
            }
            PrimaryButton(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                text = Res.string.get_started,
                onClick = { }
            )
        }
    }
}

@Composable
fun GetStartedGratefulnessItem(
    icon: DrawableResource,
    text: StringResource,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Colors.Gray40
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = stringResource(text),
            color = Colors.Gray80,
            style = TextStyles.textMdRegular()
        )
    }
}

@Composable
@Preview
private fun Preview() {
    GetStartedGratefulnessContent()
}
