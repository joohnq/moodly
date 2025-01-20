package com.joohnq.shared_resources.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

object TextStyles {
    @Composable
    fun HeadingLgExtraBold(): TextStyle = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 48.sp
    )

    @Composable
    fun HeadingSpanSmExtraBold(): SpanStyle = SpanStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 30.sp
    )

    @Composable
    fun Heading2xlExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 72.sp
    )

    @Composable
    fun HeadingMdExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 36.sp
    )

    @Composable
    fun HeadingSmExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 30.sp,
    )

    @Composable
    fun ParagraphLg() = TextStyle(
        fontFamily = FontFamily.Urbanist.Medium(),
        fontSize = 18.sp,
    )

    @Composable
    fun ParagraphMd() = TextStyle(
        fontFamily = FontFamily.Urbanist.Medium(),
        fontSize = 16.sp
    )

    @Composable
    fun ParagraphXs() = TextStyle(
        fontFamily = FontFamily.Urbanist.Medium(),
        fontSize = 12.sp
    )

    @Composable
    fun LabelSm() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 12.sp
    )

    @Composable
    fun DisplayLgExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 180.sp
    )

    @Composable
    fun DisplayMdExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 128.sp,
    )

    @Composable
    fun Text2xlExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 24.sp,
    )

    @Composable
    fun Text2xlBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 24.sp
    )

    @Composable
    fun Text2xlSemiBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.SemiBold(),
        fontSize = 24.sp
    )

    @Composable
    fun TextXlBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 20.sp
    )

    @Composable
    fun TextXlExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 20.sp
    )

    @Composable
    fun TextXlSemiBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.SemiBold(),
        fontSize = 20.sp,
    )

    @Composable
    fun TextLgExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 18.sp
    )

    @Composable
    fun TextLgBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 18.sp
    )

    @Composable
    fun TextLgSemiBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.SemiBold(),
        fontSize = 18.sp
    )

    @Composable
    fun TextMdExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 18.sp
    )

    @Composable
    fun TextMdBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 16.sp,
    )

    @Composable
    fun TextMdSemiBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.SemiBold(),
        fontSize = 16.sp
    )

    @Composable
    fun TextSmExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 14.sp,
    )

    @Composable
    fun TextSmBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 14.sp,
    )

    @Composable
    fun TextSmSemiBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.SemiBold(),
        fontSize = 14.sp
    )

    @Composable
    fun TextXsExtraBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 12.sp
    )

    @Composable
    fun TextXsBold() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 12.sp,
    )
}