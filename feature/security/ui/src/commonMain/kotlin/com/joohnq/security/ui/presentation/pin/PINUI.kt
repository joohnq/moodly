package com.joohnq.security.ui.presentation.pin

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.security.ui.presentation.pin.event.PINEvent
import com.joohnq.security.ui.presentation.pin.state.PINState
import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModelIntent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ContinueButton
import com.joohnq.shared_resources.components.OTPInputField
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.enter_a_four_digit_pin
import com.joohnq.shared_resources.pin_setup
import com.joohnq.shared_resources.scan_with_your_device_security
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource


@Composable
fun PINUI(state: PINState) {
    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        snackBarHostState = state.snackBarState,
        modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) { detectTapGestures(onTap = { state.onEvent(PINEvent.OnClearFocus) }) }
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
                TopBar(
                    modifier = Modifier.fillMaxWidth(),
                    text = Res.string.pin_setup,
                    onGoBack = { state.onEvent(PINEvent.OnGoBack) },
                )
                VerticalSpacer(60.dp)
                Text(
                    text = stringResource(Res.string.enter_a_four_digit_pin),
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
                VerticalSpacer(48.dp)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        alignment = Alignment.CenterHorizontally
                    )
                ) {
                    state.pinViewModelState.code.forEachIndexed { i, number ->
                        OTPInputField(
                            modifier = Modifier.weight(1f),
                            number = number,
                            focusRequester = state.focusRequesters[i],
                            onFocusChanged = { isFocused ->
                                if (isFocused) {
                                    state.onAction(PINViewModelIntent.OnChangeFieldFocused(i))
                                }
                            },
                            onNumberChanged = { newNumber ->
                                state.onAction(
                                    PINViewModelIntent.OnEnterNumber(
                                        index = i,
                                        number = newNumber
                                    )
                                )
                            },
                            onKeyboardBack = { state.onAction(PINViewModelIntent.OnKeyboardBack) },
                        )
                    }
                }
            }
            ContinueButton(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                enabled = state.canContinue,
                onClick = { state.onEvent(PINEvent.OnContinue) }
            )
        }
    }
}