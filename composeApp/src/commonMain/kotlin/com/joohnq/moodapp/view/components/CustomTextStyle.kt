package com.joohnq.moodapp.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.joohnq.moodapp.CustomColors
import com.joohnq.moodapp.CustomFontFamily

object CustomTextStyle {
    @Composable
    fun TextStyleWelcomeScreenTitle() = SpanStyle(
        fontFamily = CustomFontFamily.Urbanist.ExtraBold(),
        color = CustomColors.Brown80,
        fontSize = 30.sp
    )

    @Composable
    fun TextStyleWelcomeScreenTitleWord() = SpanStyle(
        fontFamily = CustomFontFamily.Urbanist.ExtraBold(),
        color = CustomColors.Brown80,
        fontSize = 30.sp
    )

    @Composable
    fun TextStyleWelcomeScreenSubTitle() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.Medium(),
        color = CustomColors.Gray60,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )

    @Composable
    fun TextStyleWelcomeScreenButton() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.ExtraBold(),
        fontSize = 18.sp
    )

    @Composable
    fun TextStyleWelcomeScreenText() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.Bold(),
        color = CustomColors.Gray60,
        fontSize = 14.sp
    )

    @Composable
    fun TextStyleWelcomeScreenButton2() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.Bold(),
        color = CustomColors.Orange40,
        fontSize = 14.sp
    )

    @Composable
    fun TextStyleWelcomeScreenIndicatorIndex() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.ExtraBold(),
        fontSize = 14.sp
    )

    @Composable
    fun TextStyleOnboardingScreenSession() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.ExtraBold(),
        color = CustomColors.Brown80,
        fontSize = 20.sp
    )

    @Composable
    fun TextStyleOnboardingScreenTitle() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.ExtraBold(),
        color = CustomColors.Brown80,
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )

    @Composable
    fun TextStyleOnboardingScreenMood() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.SemiBold(),
        color = CustomColors.Alpha100,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )

    @Composable
    fun TextStyleOnboardingSleepQualityTitle() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.ExtraBold(),
        fontSize = 18.sp,
    )

    @Composable
    fun TextStyleOnboardingSleepQualitySubTitle() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.ExtraBold(),
        fontSize = 12.sp,
    )

    @Composable
    fun TextStyleOnboardingMedicationsGridItem() = TextStyle(
        fontFamily = CustomFontFamily.Urbanist.Bold(),
        fontSize = 16.sp,
    )
}