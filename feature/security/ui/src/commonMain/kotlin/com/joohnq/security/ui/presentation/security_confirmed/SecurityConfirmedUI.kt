package com.joohnq.security.ui.presentation.security_confirmed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.security.ui.presentation.security_confirmed.event.SecurityConfirmedEvent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ContinueButton
import com.joohnq.shared_resources.components.DecoratedConvexPanel
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.now_every_time_you_open_the_app
import com.joohnq.shared_resources.security_setup_completed
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SecurityConfirmedUI(
    onEvent: (SecurityConfirmedEvent) -> Unit,
) {
    DecoratedConvexPanel(
        panelBackgroundColor = Colors.Green20,
        backgroundColor = Colors.White,
        panelContent = {
            Image(
                painter = painterResource(Drawables.Images.SecurityConfirmedBackground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(color = Colors.Green10),
                modifier = Modifier.fillMaxSize()
            )
            Image(
                painter = painterResource(Drawables.Images.SecurityConfirmedImage),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                VerticalSpacer(30.dp)
                Text(
                    text = stringResource(Res.string.security_setup_completed),
                    style = TextStyles.HeadingSmExtraBold(),
                    color = Colors.Brown80,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.paddingHorizontalMedium()
                )
                VerticalSpacer(12.dp)
                Text(
                    text = stringResource(Res.string.now_every_time_you_open_the_app),
                    style = TextStyles.ParagraphLg(),
                    color = Colors.Brown100Alpha64,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.paddingHorizontalMedium()
                )
            }
            ContinueButton(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                onClick = { onEvent(SecurityConfirmedEvent.OnContinue) }
            )
        }
    )
}