package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.isDigitsOnly
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles


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
                println(value.text)
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
                    event.key == Key.Backspace

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
            },
        )
    }
}
