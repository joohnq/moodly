package com.joohnq.security.ui.presentation.pin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.security.ui.presentation.pin.event.PINEvent
import com.joohnq.security.ui.presentation.pin.state.PINState
import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModelIntent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ContinueButton
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.enter_a_four_digit_pin
import com.joohnq.shared_resources.isDigitsOnly
import com.joohnq.shared_resources.pin_setup
import com.joohnq.shared_resources.scan_with_your_device_security
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun OTPInputField(
    modifier: Modifier = Modifier,
    number: Int? = null,
    focusRequester: FocusRequester,
    onFocusChanged: (Boolean) -> Unit,
    onNumberChanged: (Int?) -> Unit,
    onKeyboardBack: () -> Unit,
) {
    val text by remember(number) {
        mutableStateOf(
            TextFieldValue(
                text = number?.toString().orEmpty(), selection = TextRange(
                    index = if (number != null) 1 else 0
                )
            )
        )
    }
    var isFocused by remember {
        mutableStateOf(false)
    }
    val textColor = when {
        isFocused -> Colors.White
        else -> Colors.Brown80
    }
    val backgroundColor = if (isFocused) Colors.Green50 else Colors.White

    Box(
        modifier = modifier.background(color = backgroundColor, shape = Dimens.Shape.Circle)
            .aspectRatio(2 / 3f).then(
                if (isFocused) {
                    Modifier.border(
                        width = 4.dp,
                        color = Colors.Green50Alpha25,
                        shape = Dimens.Shape.Circle
                    )
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = text,
            onValueChange = { value ->
                val new = value.text
                if (new.length <= 1 && new.isDigitsOnly()) {
                    onNumberChanged(new.toIntOrNull())
                }
            },
            singleLine = true,
            cursorBrush = SolidColor(Colors.Transparent),
            textStyle = TextStyles.HeadingLgExtraBold()
                .copy(color = textColor, textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            modifier = Modifier.padding(10.dp).focusRequester(focusRequester).onFocusChanged {
                isFocused = it.isFocused
                onFocusChanged(it.isFocused)
            }.onPreviewKeyEvent { event ->
                val wasPressed =
                    event.key == Key.Backspace || event.key.keyCode == 4.toLong()
                if (wasPressed && number == null) {
                    onKeyboardBack()
                }
                false
            },
            decorationBox = { innerBox ->
                innerBox()
                if (!isFocused && number == null) {
                    Text(
                        text = "0",
                        style = TextStyles.HeadingLgExtraBold()
                            .copy(color = Colors.Brown100Alpha64),
                        color = Colors.Brown100Alpha64,
                        modifier = Modifier.fillMaxSize().wrapContentSize()
                    )
                }
            }
        )
    }
}

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
                onClick = { state.onEvent(PINEvent.OnContinue) }
            )
        }
    }
}