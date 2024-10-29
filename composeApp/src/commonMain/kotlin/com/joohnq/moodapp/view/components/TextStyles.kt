package com.joohnq.moodapp.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.FontFamily

object TextStyles {
    @Composable
    fun WelcomeScreenTitle() = SpanStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        color = Colors.Brown80,
        fontSize = 30.sp
    )

    @Composable
    fun WelcomeScreenTitleWord() = SpanStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        color = Colors.Brown80,
        fontSize = 30.sp
    )

    @Composable
    fun WelcomeScreenSubTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.Medium(),
        color = Colors.Gray60,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )

    @Composable
    fun WelcomeScreenButton() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 18.sp
    )

    @Composable
    fun WelcomeScreenText() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        color = Colors.Gray60,
        fontSize = 14.sp
    )

    @Composable
    fun WelcomeScreenButton2() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        color = Colors.Orange40,
        fontSize = 14.sp
    )

    @Composable
    fun WelcomeScreenIndicatorIndex() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 14.sp
    )

    @Composable
    fun OnboardingScreenSession() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        color = Colors.Brown80,
        fontSize = 20.sp
    )

    @Composable
    fun OnboardingScreenTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        color = Colors.Brown80,
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )

    @Composable
    fun OnboardingScreenMood() = TextStyle(
        fontFamily = FontFamily.Urbanist.SemiBold(),
        color = Colors.Brown100Alpha64,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )

    @Composable
    fun OnboardingSleepQualityTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 18.sp,
    )

    @Composable
    fun OnboardingSleepQualitySubTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 12.sp,
    )

    @Composable
    fun OnboardingMedicationsGridItem() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 16.sp,
    )

    @Composable
    fun StressRate() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 180.sp,
        color = Colors.Brown80
    )

    @Composable
    fun StressRateDesc() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 18.sp,
        color = Colors.Brown80
    )

    @Composable
    fun ExpressionAnalysisDesc() = TextStyle(
        fontFamily = FontFamily.Urbanist.Medium(),
        fontSize = 16.sp,
        color = Colors.Brown100Alpha64,
        textAlign = TextAlign.Center
    )

    @Composable
    fun ExpressionAnalysisTextField() = TextStyle(
        fontFamily = FontFamily.Urbanist.SemiBold(),
        fontSize = 30.sp,
        color = Colors.Brown100Alpha64
    )

    @Composable
    fun CompilingDataTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 30.sp,
        color = Colors.White
    )

    @Composable
    fun TextFieldLabel() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 14.sp,
        color = Colors.Brown80
    )

    @Composable
    fun TextFieldText() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 16.sp,
    )

    @Composable
    fun TextFieldErrorText() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 12.sp,
        color = Colors.Orange40
    )

    @Composable
    fun HomeTopBarDate() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 12.sp,
        color = Colors.Brown100Alpha64
    )

    @Composable
    fun HomeTopBarName() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 30.sp,
        color = Colors.Brown80
    )

    @Composable
    fun HomeTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 18.sp,
        color = Colors.Brown80
    )

    @Composable
    fun HomeMetricTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.Bold(),
        fontSize = 16.sp,
        color = Colors.White
    )

    @Composable
    fun FreudScore() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 24.sp,
        color = Colors.White,
        textAlign = TextAlign.Center
    )

    @Composable
    fun FreudTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.SemiBold(),
        fontSize = 14.sp,
        color = Colors.Green20,
        textAlign = TextAlign.Center
    )

    @Composable
    fun MoodComponentText() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 24.sp,
        color = Colors.White
    )

    @Composable
    fun HealthJournalDay() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 9.sp,
        color = Colors.Purple40
    )

    @Composable
    fun MindfulTrackerCardTitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 18.sp,
        color = Colors.Brown80
    )

    @Composable
    fun MindfulTrackerCardSubtitle() = TextStyle(
        fontFamily = FontFamily.Urbanist.SemiBold(),
        fontSize = 14.sp,
        color = Colors.Brown100Alpha64
    )

    @Composable
    fun SleepQualityOption() = TextStyle(
        fontFamily = FontFamily.Urbanist.ExtraBold(),
        fontSize = 12.sp,
        color = Colors.Brown80
    )
}