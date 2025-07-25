package com.joohnq.shared_resources.components.input_field

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppOutlinedTextField(
    text: String,
    placeholder: StringResource,
    errorText: String?,
    colors: TextFieldColors,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = text,
        isError = errorText != null,
        onValueChange = onValueChange,
        modifier = modifier,
        shape = Dimens.Shape.Circle,
        placeholder = {
            Text(
                text = stringResource(placeholder),
                style = TextStyles.TextMdBold()
            )
        },
        singleLine = true,
        trailingIcon = trailingIcon,
        colors = colors,
        textStyle = TextStyles.TextMdBold(),
        leadingIcon = leadingIcon
    )
}