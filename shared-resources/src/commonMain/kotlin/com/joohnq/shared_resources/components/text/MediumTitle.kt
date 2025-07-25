package com.joohnq.shared_resources.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MediumTitle(
    text: StringResource,
) {
    Text(
        text = stringResource(text),
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
        style = TextStyles.textMdExtraBold(),
        color = Colors.Brown80
    )
}