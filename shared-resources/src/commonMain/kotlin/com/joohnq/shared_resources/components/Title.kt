package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Title(modifier: Modifier = Modifier, text: StringResource) {
    Text(
        text = stringResource(text),
        style = TextStyles.TextLgExtraBold(),
        color = Colors.Brown80,
        modifier = modifier
    )
}

@Composable
fun MediumTitle(text: StringResource) {
    Text(
        text = stringResource(text),
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
        style = TextStyles.TextMdExtraBold(),
        color = Colors.Brown80
    )
}

@Composable
fun SmallTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
        style = TextStyles.LabelSm(),
        color = Colors.Brown100Alpha64
    )
}