package com.joohnq.security.ui.presentation.pin

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.security.ui.components.PINCode
import com.joohnq.security.ui.presentation.pin.event.PINEvent
import com.joohnq.security.ui.presentation.pin.viewmodel.PINIntent
import com.joohnq.security.ui.presentation.pin.viewmodel.PINState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ContinueButton
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
fun PINUI(
    snackBarState: SnackbarHostState = SnackbarHostState(),
    state: PINState,
    focusRequesters: List<FocusRequester>,
    focusManager: FocusManager,
    keyboardManager: SoftwareKeyboardController?,
    onAction: (PINIntent) -> Unit,
    onEvent: (PINEvent) -> Unit,
    canContinue: Boolean,
) {
    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        snackBarHostState = snackBarState,
        modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) { detectTapGestures(onTap = { onEvent(PINEvent.OnClearFocus) }) }
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
                    onGoBack = { onEvent(PINEvent.OnGoBack) },
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
                PINCode(
                    code = state.code,
                    focusedIndex = state.focusedIndex,
                    onNumberChanged = { i, newNumber ->
                        onAction(
                            PINIntent.OnEnterNumber(
                                index = i,
                                number = newNumber
                            )
                        )
                    },
                    onKeyboardBack = { onAction(PINIntent.OnKeyboardBack) },
                    focusRequesters = focusRequesters,
                    focusManager = focusManager,
                    keyboardManager = keyboardManager,
                    onFocusChanged = { i -> onAction(PINIntent.OnChangeFieldFocused(i)) },
                )
            }
            ContinueButton(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                enabled = canContinue,
                onClick = { onEvent(PINEvent.OnContinue) }
            )
        }
    }
}