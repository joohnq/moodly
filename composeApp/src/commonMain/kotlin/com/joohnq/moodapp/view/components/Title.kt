package com.joohnq.moodapp.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
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