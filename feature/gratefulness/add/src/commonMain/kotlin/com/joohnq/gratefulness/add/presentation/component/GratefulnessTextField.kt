package com.joohnq.gratefulness.add.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GratefulnessTextField(
    label: StringResource,
    value: String,
    placeholder: StringResource,
    onValueChange: (String) -> Unit,
) {
    Text(
        text = stringResource(label),
        style = TextStyles.textMdMedium(),
        color = Colors.Gray60,
        textAlign = TextAlign.Center
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(placeholder),
                style = TextStyles.headingXsMedium(),
                textAlign = TextAlign.Center
            )
        },
        singleLine = false,
        textStyle =
            TextStyles
                .headingXsBold()
                .copy(
                    textAlign = TextAlign.Center
                ),
        colors =
            TextFieldDefaults.colors(
                focusedTextColor = Colors.Gray80,
                unfocusedTextColor = Colors.Gray80,
                focusedIndicatorColor = Colors.Transparent,
                unfocusedIndicatorColor = Colors.Transparent,
                focusedContainerColor = Colors.Transparent,
                unfocusedContainerColor = Colors.Transparent,
                cursorColor = Colors.Green40,
                focusedPlaceholderColor = Colors.Gray60,
                unfocusedPlaceholderColor = Colors.Gray60
            )
    )
}
