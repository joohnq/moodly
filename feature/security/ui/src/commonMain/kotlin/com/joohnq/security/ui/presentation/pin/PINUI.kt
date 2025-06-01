package com.joohnq.security.ui.presentation.pin

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.joohnq.security.ui.presentation.pin.viewmodel.PinContract
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
    state: PinContract.State,
    focusRequesters: List<FocusRequester>,
    focusManager: FocusManager,
    keyboardManager: SoftwareKeyboardController?,
    onIntent: (PinContract.Intent) -> Unit,
    onEvent: (PinContract.Event) -> Unit,
    canContinue: Boolean,
) {
    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        snackBarHostState = snackBarState,
        modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) { detectTapGestures(onTap = { onEvent(PinContract.Event.ClearFocus) }) }
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
                    onGoBack = { onEvent(PinContract.Event.GoBack) },
                )
                VerticalSpacer(60.dp)
                Text(
                    text = stringResource(Res.string.enter_a_four_digit_pin),
                    style = TextStyles.text2xlExtraBold(),
                    color = Colors.Brown80
                )
                VerticalSpacer(12.dp)
                Text(
                    text = stringResource(Res.string.scan_with_your_device_security),
                    style = TextStyles.textMdMedium(),
                    color = Colors.Brown100Alpha64,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(48.dp)
                PINCode(
                    code = state.code,
                    focusedIndex = state.focusedIndex,
                    onNumberChanged = { i, newNumber ->
                        onIntent(
                            PinContract.Intent.EnterNumber(
                                index = i,
                                number = newNumber
                            )
                        )
                    },
                    onKeyboardBack = { onIntent(PinContract.Intent.KeyboardBack) },
                    focusRequesters = focusRequesters,
                    focusManager = focusManager,
                    keyboardManager = keyboardManager,
                    onFocusChanged = { i -> onIntent(PinContract.Intent.ChangeFieldFocused(i)) },
                )
            }
            ContinueButton(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                enabled = canContinue,
                onClick = { onEvent(PinContract.Event.Continue) }
            )
        }
    }
}