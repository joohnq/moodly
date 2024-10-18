package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import org.jetbrains.compose.resources.painterResource

@Composable
fun ExpressionAnalysisTextField(text: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = null,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .heightIn(min = 250.dp).border(
                color = Colors.Brown80Alpha25,
                width = 4.dp,
                shape = RoundedCornerShape(20.dp)
            ),
        colors = TextFieldColors(
            indicatorColor = Colors.Orange40,
            containerColor = Colors.White,
            textColor = Colors.Brown80,
            placeholderColor = Colors.Brown100Alpha64,
            cursorColor = Colors.Orange40,
        ),
        textStyle = TextStyles.ExpressionAnalysisTextField()
    )
}

@Composable
fun TextFieldWithLabelAndDoubleBorder(
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
            label,
            style = TextStyles.TextFieldLabel()
        )
        Spacer(modifier = Modifier.height(5.dp))
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
                    shape = CircleShape
                )
                .padding(4.dp)
        ) {
            OutlinedTextField(
                value = name,
                isError = errorText.isNotEmpty(),
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth().height(56.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }.focusRequester(focusRequester),
                shape = CircleShape,
                placeholder = {
                    Text(
                        placeholder,
                        style = TextStyles.TextFieldText()
                    )
                },
                colors = colors,
                textStyle = TextStyles.TextFieldText(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(Drawables.Icons.User),
                        contentDescription = null,
                        tint = Colors.Brown80,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
        if (isError) {
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = Colors.Orange40,
                    shape = CircleShape
                ).background(
                    color = Colors.Orange20,
                    shape = CircleShape
                ).padding(vertical = 5.dp).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Warning),
                        contentDescription = null,
                        tint = Colors.Orange40,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = errorText,
                        style = TextStyles.TextFieldErrorText()
                    )
                }
            }
        }
    }
}

@Composable
fun UserNameTextField(
    name: String,
    errorText: String,
    onValueChange: (String) -> Unit
) {
    TextFieldWithLabelAndDoubleBorder(
        label = "Name",
        placeholder = "Enter your name...",
        name = name,
        errorText = errorText,
        focusedBorderColor = Colors.Green50Alpha25,
        colors = TextFieldColors(
            containerColor = Colors.White,
            placeholderColor = Colors.Brown100Alpha64,
            leadingIconColor = Colors.Brown80,
            textColor = Colors.Brown100Alpha64,
            cursorColor = Colors.Brown80,
            focusedBorderColor = Colors.Green50,
            errorBorderColor = Colors.Orange40,
            unfocusedBorderColor = Colors.Transparent
        ),
        onValueChange = onValueChange,
    )
}