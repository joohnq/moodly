package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
fun Title(text: StringResource) {
    Column {
        VerticalSpacer(32.dp)
        Text(
            text = stringResource(text),
            style = TextStyles.TextLgExtraBold(),
            color = Colors.Brown80,
            modifier = Modifier.paddingHorizontalMedium()
        )
        VerticalSpacer(12.dp)
    }
}

@Composable
fun MediumTitle(text: StringResource) {
    Column {
        VerticalSpacer(20.dp)
        Text(
            text = stringResource(text),
            modifier = Modifier.fillMaxWidth(),
            style = TextStyles.TextMdExtraBold(),
            color = Colors.Brown80
        )
        VerticalSpacer(10.dp)
    }
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