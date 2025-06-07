package com.joohnq.auth.ui.presentation.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AuthenticationTextFieldSection(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: DrawableResource,
) {
    Column {
        Text(
            text = label,
            style = TextStyles.textSmSemiBold(),
            color = Colors.Gray80
        )
        VerticalSpacer(5.dp)
        OutlinedTextField(
            value = value,
            singleLine = true,
            maxLines = 1,
            onValueChange = onValueChange,
            modifier = modifier,
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Colors.Brown60,
                unfocusedBorderColor = Colors.Gray30,
                errorBorderColor = Colors.Gray30,
                cursorColor = Colors.Brown60,
                focusedContainerColor = Colors.White,
                unfocusedContainerColor = Colors.White,
                focusedTrailingIconColor = Colors.Brown60,
                unfocusedTrailingIconColor = Colors.Gray60,
                errorTrailingIconColor = Colors.Gray60,
                focusedTextColor = Colors.Gray70,
                unfocusedTextColor = Colors.Gray70,
                errorTextColor = Colors.Gray70,
                focusedLeadingIconColor = Colors.Gray30,
                unfocusedLeadingIconColor = Colors.Gray30,
                errorLeadingIconColor = Colors.Gray30,
            ),
            textStyle = TextStyles.textMdRegular(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyles.textSmRegular(),
                    color = Colors.Gray60
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = placeholder,
                    tint = Colors.Gray60
                )
            }
        )
    }
}

@Composable
fun AuthenticationPasswordTextFieldSection(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: DrawableResource,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            style = TextStyles.textSmSemiBold(),
            color = Colors.Gray80
        )
        VerticalSpacer(5.dp)
        OutlinedTextField(
            value = value,
            singleLine = true,
            maxLines = 1,
            onValueChange = onValueChange,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = modifier,
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Colors.Brown60,
                unfocusedBorderColor = Colors.Gray30,
                errorBorderColor = Colors.Gray30,
                cursorColor = Colors.Brown60,
                focusedContainerColor = Colors.White,
                unfocusedContainerColor = Colors.White,
                focusedTrailingIconColor = Colors.Gray30,
                unfocusedTrailingIconColor = Colors.Gray30,
                errorTrailingIconColor = Colors.Gray30,
                focusedTextColor = Colors.Gray70,
                unfocusedTextColor = Colors.Gray70,
                errorTextColor = Colors.Gray70,
                focusedLeadingIconColor = Colors.Gray60,
                unfocusedLeadingIconColor = Colors.Gray60,
                errorLeadingIconColor = Colors.Gray60,
            ),
            textStyle = TextStyles.textMdRegular(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyles.textSmRegular(),
                    color = Colors.Gray60
                )
            },
            trailingIcon = {
                val image = if (isPasswordVisible)
                    Drawables.Icons.Filled.EyeDisabled
                else Drawables.Icons.Filled.Eye

                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = painterResource(image),
                        contentDescription = if (isPasswordVisible) "Ocultar senha" else "Mostrar senha"
                    )
                }
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = placeholder,
                    tint = Colors.Gray60
                )
            }
        )
    }
}

@Preview
@Composable
fun AuthenticationTextFieldSectionPreviewEmpty() {
    AuthenticationTextFieldSection(
        label = "Email",
        placeholder = "Enter your email address...",
        value = "",
        onValueChange = {},
        leadingIcon = Drawables.Icons.Outlined.Email
    )
}

@Preview
@Composable
fun AuthenticationTextFieldSectionPreviewFilled() {
    AuthenticationTextFieldSection(
        label = "Email",
        placeholder = "Enter your email address...",
        value = "test@gmail.com",
        onValueChange = {},
        leadingIcon = Drawables.Icons.Outlined.Email
    )
}

@Preview
@Composable
fun AuthenticationPasswordTextFieldSectionPreviewEmpty() {
    AuthenticationPasswordTextFieldSection(
        label = "Password",
        placeholder = "Enter your password...",
        value = "",
        onValueChange = {},
        leadingIcon = Drawables.Icons.Outlined.Email
    )
}

@Preview
@Composable
fun AuthenticationPasswordTextFieldSectionPreviewFilled() {
    AuthenticationPasswordTextFieldSection(
        label = "Password",
        placeholder = "Enter your password...",
        value = "12345678",
        onValueChange = {},
        leadingIcon = Drawables.Icons.Outlined.Email
    )
}