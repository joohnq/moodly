package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextWithBackground(
    text: String,
    borderColor: Color,
    backgroundColor: Color,
    textColor: Color
) {
    Text(
        text = text.uppercase(),
        modifier = Modifier
            .border(2.dp, color = borderColor, shape = Dimens.Shape.Circle)
            .background(color = backgroundColor, shape = Dimens.Shape.Circle)
            .padding(vertical = 9.dp, horizontal = 16.dp),
        style = TextStyles.LabelSm().copy(color = textColor),
    )
}

@Composable
fun TextWithBackground(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    textColor: Color,
) {
    Text(
        text = text,
        modifier = modifier
            .background(color = backgroundColor, shape = Dimens.Shape.Circle)
            .padding(vertical = 9.dp, horizontal = 16.dp),
        style = TextStyles.LabelSm().copy(color = textColor)
    )
}