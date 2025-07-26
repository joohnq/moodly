package com.joohnq.shared_resources.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: StringResource
) {
    Text(
        text = stringResource(text),
        style = TextStyles.textLgExtraBold(),
        color = Colors.Brown80,
        modifier = modifier
    )
}
