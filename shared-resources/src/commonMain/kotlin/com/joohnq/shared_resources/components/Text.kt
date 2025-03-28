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
fun TextWithBackground(text: String, borderColor: Color, backgroundColor: Color, textColor: Color) {
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

@Composable
fun TextWithSpan(
    firstTitle: StringResource? = null,
    secondTitle: StringResource? = null,
    span: String,
    spanColor: Color,
) {
    Text(
        text = buildAnnotatedString {
            if (firstTitle != null) {
                withStyle(
                    style = TextStyles.HeadingSpanSmExtraBold()
                        .copy(color = Colors.Brown80)
                ) {
                    append(stringResource(firstTitle))
                }
                append(" ")
            }
            withStyle(
                style = TextStyles.HeadingSpanSmExtraBold()
                    .copy(color = spanColor)
            ) {
                append(span)
            }
            if (secondTitle != null) {
                append(" ")
                withStyle(
                    style = TextStyles.HeadingSpanSmExtraBold()
                        .copy(color = Colors.Brown80)
                ) {
                    append(stringResource(secondTitle))
                }
            }
        },
        lineHeight = 40.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextEllipsis(
    text: String,
    style: TextStyle,
    color: Color,
    maxLines: Int = 1,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        maxLines = maxLines,
        style = style,
        color = color,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis
    )
}