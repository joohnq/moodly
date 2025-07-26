package com.joohnq.security.impl.ui.presentation.pin

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.security.impl.ui.components.PinCode
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.continue_word
import com.joohnq.shared_resources.enter_a_four_digit_pin
import com.joohnq.shared_resources.pin_setup
import com.joohnq.shared_resources.scan_with_your_device_security
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun PinContent(
    snackBarState: SnackbarHostState = SnackbarHostState(),
    state: PinContract.State,
    focusRequesters: List<FocusRequester> = emptyList(),
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardManager: SoftwareKeyboardController? = null,
    onAction: (PinContract.Intent) -> Unit = {},
    onEvent: (PinContract.Event) -> Unit = {},
) {
    AppScaffoldLayout(
        containerColor = Colors.Brown10,
        snackBarHostState = snackBarState,
        modifier =
            Modifier
                .fillMaxSize()
                .pointerInput(Unit) { detectTapGestures(onTap = { onEvent(PinContract.Event.OnClearFocus) }) }
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .padding(padding)
                    .padding(bottom = 20.dp)
                    .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.paddingHorizontalMedium(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTopBar(
                    modifier = Modifier.fillMaxWidth(),
                    text = Res.string.pin_setup,
                    onGoBack = { onEvent(PinContract.Event.OnGoBack) }
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
                    style = TextStyles.paragraphMd(),
                    color = Colors.Brown100Alpha64,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(48.dp)
                PinCode(
                    code = state.code,
                    focusedIndex = state.focusedIndex,
                    onNumberChanged = { i, newNumber ->
                        onAction(
                            PinContract.Intent.OnEnterNumber(
                                index = i,
                                number = newNumber
                            )
                        )
                    },
                    onKeyboardBack = { onAction(PinContract.Intent.OnKeyboardBack) },
                    focusRequesters = focusRequesters,
                    focusManager = focusManager,
                    keyboardManager = keyboardManager,
                    onFocusChanged = { i -> onAction(PinContract.Intent.OnChangeFieldFocused(i)) }
                )
            }
            PrimaryButton(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                text = Res.string.continue_word,
                onClick = { onEvent(PinContract.Event.OnContinue) }
            )
        }
    }
}
