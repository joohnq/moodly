package com.joohnq.security.ui.presentation.biometric_faceid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.security.ui.presentation.biometric_faceid.event.BiometricFaceIdEvent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ContinueButton
import com.joohnq.shared_resources.components.TopBalloon
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.this_password_is_the_same_validation_method
import com.joohnq.shared_resources.use_device_password
import com.joohnq.shared_resources.use_your_authentication_to_securely
import com.joohnq.shared_resources.welcome_back
import org.jetbrains.compose.resources.stringResource

@Composable
fun BiometricFaceIdUI(
    onEvent: (BiometricFaceIdEvent) -> Unit,
) {
    BoxWithConstraints(modifier = Modifier.background(color = Colors.Brown10)) {
        TopBalloon(
            backgroundColor = Colors.Brown80,
            iconColor = Colors.White
        )
        Scaffold(
            containerColor = Colors.Brown10,
            modifier = Modifier.padding(top = maxWidth / 2 + 56.dp)
        ) { padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).paddingHorizontalMedium(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    VerticalSpacer(40.dp)
                    Text(
                        text = stringResource(Res.string.welcome_back),
                        style = TextStyles.HeadingSmExtraBold(),
                        color = Colors.Brown80
                    )
                    VerticalSpacer(10.dp)
                    Text(
                        text = stringResource(Res.string.use_your_authentication_to_securely),
                        style = TextStyles.ParagraphLg(),
                        color = Colors.Brown100Alpha64,
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ContinueButton(
                        text = Res.string.use_device_password,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onEvent(BiometricFaceIdEvent.OnContinue) }
                    )
                    VerticalSpacer(16.dp)
                    Text(
                        text = stringResource(Res.string.this_password_is_the_same_validation_method),
                        style = TextStyles.TextSmMedium(),
                        color = Colors.Brown100Alpha64,
                        textAlign = TextAlign.Center
                    )
                    VerticalSpacer(10.dp)
                }
            }
        }
    }
}