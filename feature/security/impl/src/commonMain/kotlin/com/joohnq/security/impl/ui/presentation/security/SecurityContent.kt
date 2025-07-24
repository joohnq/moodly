package com.joohnq.security.impl.ui.presentation.security

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.security.impl.ui.presentation.security.event.SecurityEvent
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.button.ContinueButton
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.components.button.SecondaryButton
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SecurityContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    onEvent: (SecurityEvent) -> Unit = {},
) {
    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        snackBarHostState = snackBarState,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(bottom = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.paddingHorizontalMedium(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VerticalSpacer(10.dp)
                Text(
                    text = stringResource(Res.string.security_setup),
                    style = TextStyles.TextXlExtraBold(),
                    color = Colors.Brown80,
                )
                VerticalSpacer(60.dp)
                Image(
                    painter = painterResource(Drawables.Images.Security),
                    contentDescription = stringResource(Res.string.security_setup),
                    modifier = Modifier.fillMaxWidth().paddingHorizontalMedium()
                )
                VerticalSpacer(60.dp)
                Text(
                    text = stringResource(Res.string.security_setup),
                    style = TextStyles.Text2xlExtraBold(),
                    color = Colors.Brown80
                )
                VerticalSpacer(12.dp)
                Text(
                    text = stringResource(Res.string.scan_with_your_device_security),
                    style = TextStyles.ParagraphMd(),
                    color = Colors.Brown100Alpha64,
                    textAlign = TextAlign.Center
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                ContinueButton(
                    modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                    onClick = { onEvent(SecurityEvent.OnContinue) }
                )
                SecondaryButton(
                    modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                    text = Res.string.set_a_pin,
                    onClick = { onEvent(SecurityEvent.OnSetPin) }
                )
                SecondaryButton(
                    modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                    text = Res.string.skip,
                    onClick = { onEvent(SecurityEvent.OnSkip) }
                )
            }
        }
    }
}