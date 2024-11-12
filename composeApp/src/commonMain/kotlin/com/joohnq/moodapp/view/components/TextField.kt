package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingVerticalSmall
import com.joohnq.moodapp.view.ui.TextStyles
import org.jetbrains.compose.resources.painterResource

@Composable
fun ExpressionAnalysisTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = null,
        shape = Dimens.Shape.Medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .heightIn(min = 250.dp).border(
                color = Colors.Brown80Alpha25,
                width = 4.dp,
                shape = Dimens.Shape.Medium
            ),
        colors = ComponentColors.TextField.ExpressionAnalysisColors(),
        textStyle = TextStyles.HeadingSmSemiBold(),
    )
}

@Composable
fun TextFieldWithLabelAndDoubleBorder(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    name: String,
    errorText: String,
    colors: TextFieldColors,
    focusedBorderColor: Color,
    onValueChange: (String) -> Unit,
) {
    val isError = errorText.isNotEmpty()
    val focusRequester = FocusRequester()
    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(isError) {
        if (isError)
            focusRequester.requestFocus()
    }

    Column {
        Text(
            text = label,
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
            OutlinedTextField(
                value = name,
                isError = errorText.isNotEmpty(),
                onValueChange = onValueChange,
                modifier = modifier.fillMaxWidth().height(56.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }.focusRequester(focusRequester),
                shape = Dimens.Shape.Circle,
                placeholder = {
                    Text(
                        text = placeholder,
                        style = TextStyles.TextMdBold()
                    )
                },
                colors = colors,
                textStyle = TextStyles.TextMdBold(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(Drawables.Icons.User),
                        contentDescription = null,
                        tint = Colors.Brown80,
                        modifier = Modifier.size(Dimens.Icon)
                    )
                }
            )
        }
        if (isError) {
            VerticalSpacer(4.dp)
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
                        painter = painterResource(Drawables.Icons.Warning),
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
    }
}

@Composable
fun UserNameTextField(
    modifier: Modifier = Modifier,
    name: String,
    errorText: String,
    onValueChange: (String) -> Unit
) {
    TextFieldWithLabelAndDoubleBorder(
        modifier = modifier,
        label = "Name",
        placeholder = "Enter your name...",
        name = name,
        errorText = errorText,
        focusedBorderColor = Colors.Green50Alpha25,
        colors = ComponentColors.TextField.UserNameColors(),
        onValueChange = onValueChange,
    )
}