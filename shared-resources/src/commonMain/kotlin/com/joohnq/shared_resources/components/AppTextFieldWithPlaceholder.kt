package com.joohnq.shared_resources.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.view.ErrorView
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppTextFieldWithPlaceholder(
    modifier: Modifier = Modifier,
    label: StringResource,
    placeholder: StringResource,
    text: String,
    errorText: String?,
    colors: TextFieldColors,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    focusedBorderColor: Color,
    onValueChange: (String) -> Unit,
) {
    val isError = errorText != null
    val focusRequester = FocusRequester()
    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(isError) {
        if (isError)
            focusRequester.requestFocus()
    }

    Column {
        Text(
            text = stringResource(label),
            style = TextStyles.TextSmExtraBold(),
            color = Colors.Brown80
        )
        VerticalSpacer(5.dp)
        Box(
            modifier = Modifier
                .border(
                    width = when (true) {
                        isError -> 4.dp
                        isFocused -> 4.dp
                        else -> 0.dp
                    },
                    color = when (true) {
                        isError -> Colors.Orange40Alpha25
                        isFocused -> focusedBorderColor
                        else -> Colors.Transparent
                    },
                    shape = Dimens.Shape.Circle
                )
                .padding(4.dp)
        ) {
            AppOutlinedTextField(
                text = text,
                placeholder = placeholder,
                errorText = errorText,
                colors = colors,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                modifier = modifier.fillMaxWidth().height(56.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }.focusRequester(focusRequester),
                onValueChange = onValueChange,
            )
        }
        if (isError) {
            VerticalSpacer(4.dp)
            ErrorView(errorText.toString())
        }
    }
}