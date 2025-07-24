package com.joohnq.shared_resources.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HeadingWithSpan(
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