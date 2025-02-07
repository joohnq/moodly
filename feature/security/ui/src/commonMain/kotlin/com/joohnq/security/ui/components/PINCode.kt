package com.joohnq.security.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.OTPInputField

@Composable
fun PINCode(
    code: List<Int?>,
    focusedIndex: Int?,
    focusRequesters: List<FocusRequester>,
    focusManager: FocusManager,
    keyboardManager: SoftwareKeyboardController?,
    onNumberChanged: (i: Int, newNumber: Int?) -> Unit,
    onKeyboardBack: () -> Unit,
    onFocusChanged: (i: Int) -> Unit,
) {
    LaunchedEffect(code) {
        val allNumbersEntered = code.none { it == null }
        if (allNumbersEntered) {
            focusRequesters.forEach {
                it.freeFocus()
            }
            focusManager.clearFocus()
            keyboardManager?.hide()
        }
    }

    LaunchedEffect(focusedIndex) {
        focusedIndex?.let { i ->
            focusRequesters.getOrNull(i)?.requestFocus()
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(
            10.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        code.forEachIndexed { i, number ->
            OTPInputField(
                modifier = Modifier.weight(1f),
                number = number,
                focusRequester = focusRequesters[i],
                onFocusChanged = { isFocused ->
                    if (isFocused) {
                        onFocusChanged(i)
                    }
                },
                onNumberChanged = { newNumber -> onNumberChanged(i, newNumber) },
                onKeyboardBack = onKeyboardBack
            )
        }
    }
}