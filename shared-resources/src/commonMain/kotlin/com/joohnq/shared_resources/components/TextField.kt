package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.theme.*
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingVerticalSmall
import com.joohnq.shared_resources.write_what_are_you_feeling
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorInfo(errorText: String) {
    Box(
        modifier = Modifier.border(
            width = 1.dp,
            color = Colors.Orange40,
            shape = Dimens.Shape.Circle
        ).background(
            color = Colors.Orange20,
            shape = Dimens.Shape.Circle
        ).paddingVerticalSmall().fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(Drawables.Icons.Filled.Warning),
                contentDescription = null,
                tint = Colors.Orange40,
                modifier = Modifier.size(20.dp)
            )
            HorizontalSpacer(10.dp)
            Text(
                text = errorText,
                style = TextStyles.TextXsExtraBold(),
                color = Colors.Orange40
            )
        }
    }
}

@Composable
fun ExpressionAnalysisTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        label = null,
        shape = Dimens.Shape.Large,
        placeholder = {
            Text(
                text = stringResource(Res.string.write_what_are_you_feeling),
                style = TextStyles.Text2xlBold(),
                color = Colors.Brown100Alpha64
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 250.dp)
            .shadow(
                elevation = 7.dp,
                ambientColor = Colors.Black48.copy(alpha = 0.2f),
                spotColor = Colors.Black48.copy(alpha = 0.2f),
                shape = Dimens.Shape.Large
            ),
        colors = ComponentColors.TextField.ExpressionAnalysisColors(),
        textStyle = TextStyles.Text2xlBold(),
    )
}

@Composable
fun TextFieldWithLabelAndDoubleBorder(
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
            CustomOutlinedTextField(
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
            ErrorInfo(errorText.toString())
        }
    }
}

@Composable
fun CustomOutlinedTextField(
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

@Preview
@Composable
fun ExpressionAnalysisTextFieldPreview() {
    ExpressionAnalysisTextField(
        text = "",
        onValueChange = {}
    )
}