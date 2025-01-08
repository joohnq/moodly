package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingVerticalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    isDark: Boolean = true,
    text: StringResource? = null,
    onGoBack: () -> Unit,
    content: (@Composable () -> Unit)? = null,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().paddingVerticalMedium()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BackButton(
                color = if (isDark) Colors.Brown80 else Colors.White,
                onClick = onGoBack
            )
            text?.let {
                HorizontalSpacer(10.dp)

                Text(
                    text = stringResource(text),
                    style = TextStyles.TextXlExtraBold(),
                    color = if (isDark) Colors.Brown80 else Colors.White,
                )
            }
        }
        content?.let { it() }
    }
}
