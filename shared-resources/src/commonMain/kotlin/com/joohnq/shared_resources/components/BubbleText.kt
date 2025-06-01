package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joohnq.domain.entity.TextRadioButtonColors
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import org.jetbrains.compose.resources.StringResource

@Composable
fun BubbleText(
    text: StringResource,
    fontSize: TextUnit = 18.sp,
    padding: Dp = 24.dp,
    onClick: () -> Unit = {},
    selected: Boolean,
) {
    val size = CalculateTextWidth(text, fontSize)
    Box(modifier = Modifier.size(size + padding)) {
        TextRadioButton(
            onClick = onClick,
            modifier = Modifier
                .size(size + padding),
            colors = TextRadioButtonColors(
                selectedBackgroundColor = Colors.Green50,
                selectedContentColor = Colors.White,
                selectedBorderColor = Colors.Green50Alpha25,
                unSelectedBackgroundColor = Colors.Brown20,
                unSelectedContentColor = Colors.Brown40
            ),
            contentPaddingValues = PaddingValues(0.dp),
            text = text,
            selected = selected,
            shape = Dimens.Shape.Circle
        )
    }
}