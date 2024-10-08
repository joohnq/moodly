package com.joohnq.moodapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object CustomTextStyle {
    @Composable
    fun TextStyleWelcomeScreenTitle() = SpanStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        color = Colors.Brown80,
        fontSize = 30.sp
    )

    @Composable
    fun TextStyleWelcomeScreenTitleWord() = SpanStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        color = Colors.Brown80,
        fontSize = 30.sp
    )

    @Composable
    fun TextStyleWelcomeScreenSubTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.Medium(),
        color = Colors.Gray60,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )

    @Composable
    fun TextStyleWelcomeScreenButton() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        color = Colors.White,
        fontSize = 18.sp
    )

    @Composable
    fun TextStyleWelcomeScreenText() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        color = Colors.Gray60,
        fontSize = 14.sp
    )

    @Composable
    fun TextStyleWelcomeScreenButton2() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        color = Colors.Orange40,
        fontSize = 14.sp
    )

    @Composable
    fun TextStyleWelcomeScreenIndicatorIndex() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 14.sp
    )

    @Composable
    fun TextStyleOnboardingScreenSession() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        color = Colors.Orange80,
        fontSize = 20.sp
    )
}