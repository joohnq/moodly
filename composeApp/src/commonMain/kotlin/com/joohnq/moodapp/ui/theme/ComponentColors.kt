package com.joohnq.moodapp.ui.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.SliderColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.joohnq.moodapp.domain.TextRadioButtonColors

object ComponentColors {
    object RadioButton {
        @Composable fun TextRadioButtonColors(): TextRadioButtonColors = TextRadioButtonColors(
            selectedBackgroundColor = Colors.Green50,
            selectedContentColor = Colors.White,
            unSelectedContentColor = Colors.Brown80,
            unSelectedBackgroundColor = Colors.White,
            selectedBorderColor = Colors.Green50Alpha25,
        )

        @Composable
        fun IconAndTextRadioButtonHorizontalColors(colors: TextRadioButtonColors): RadioButtonColors =
            RadioButtonColors(
                selectedColor = colors.selectedContentColor,
                unselectedColor = colors.unSelectedContentColor,
                disabledSelectedColor = colors.selectedContentColor,
                disabledUnselectedColor = colors.unSelectedContentColor,
            )

        @Composable fun StressLevelRadioButtonColors(): TextRadioButtonColors =
            TextRadioButtonColors(
                selectedBackgroundColor = Colors.Orange40,
                selectedContentColor = Colors.White,
                unSelectedContentColor = Colors.Brown80,
                unSelectedBackgroundColor = Colors.White,
                selectedBorderColor = Colors.Orange40Alpha25,
            )
    }

    object NavigationBarItem {
        @Composable fun NavigationBarItemColors(): NavigationBarItemColors =
            NavigationBarItemColors(
                selectedIconColor = Colors.Brown80,
                selectedTextColor = Colors.Brown80,
                selectedIndicatorColor = Colors.Brown10,
                unselectedIconColor = Colors.Brown30,
                unselectedTextColor = Colors.Brown30,
                disabledIconColor = Colors.Brown30,
                disabledTextColor = Colors.Brown30
            )
    }

    object Slider {
        @Composable fun SleepQualitySliderColors(): SliderColors = SliderColors(
            thumbColor = Colors.Orange40,
            activeTickColor = Colors.Orange40,
            inactiveTickColor = Colors.Brown20,
            activeTrackColor = Colors.Orange40,
            inactiveTrackColor = Colors.Brown20,
            disabledThumbColor = Colors.Brown20,
            disabledActiveTrackColor = Colors.Orange40,
            disabledActiveTickColor = Colors.Orange40,
            disabledInactiveTrackColor = Colors.Brown20,
            disabledInactiveTickColor = Colors.Brown20
        )
    }

    object Card {
        @Composable fun MentalHealthMetricColors(backgroundColor: Color): CardColors = CardColors(
            containerColor = backgroundColor,
            contentColor = Colors.White,
            disabledContainerColor = backgroundColor,
            disabledContentColor = Colors.White
        )

        @Composable fun MainCardColors(): CardColors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Brown80
        )

        @Composable fun StressLevelCardColors(): CardColors = CardColors(
            containerColor = Colors.Brown10,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.Brown10,
            disabledContentColor = Colors.Brown80
        )
    }

    object IconButton {
        @Composable fun ContinueButtonColors(): IconButtonColors = IconButtonColors(
            containerColor = Colors.Brown80,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Brown80,
            disabledContentColor = Colors.White
        )

        @Composable fun PreviousNextButton(color: Color): IconButtonColors = IconButtonColors(
            containerColor = Colors.Transparent,
            disabledContainerColor = Colors.Transparent,
            contentColor = color,
            disabledContentColor = Color.Transparent,
        )
    }

    object Button {
        @Composable fun BottomNavigationAddButtonColors(): ButtonColors = ButtonColors(
            containerColor = Colors.Green50,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Green50,
            disabledContentColor = Colors.White
        )

        @Composable fun MainButtonColors(): ButtonColors = ButtonColors(
            containerColor = Colors.Brown80,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Brown80,
            disabledContentColor = Colors.White
        )

        @Composable fun MainButtonColorsInverted(): ButtonColors = ButtonColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Brown80
        )

        @Composable fun TextRadioButtonColors(
            selected: Boolean,
            colors: TextRadioButtonColors
        ): ButtonColors = ButtonColors(
            containerColor = if (selected) colors.selectedBackgroundColor else colors.unSelectedBackgroundColor,
            contentColor = if (selected) colors.selectedContentColor else colors.unSelectedContentColor,
            disabledContainerColor = if (selected) colors.selectedBackgroundColor else colors.unSelectedBackgroundColor,
            disabledContentColor = if (selected) colors.selectedContentColor else colors.unSelectedContentColor
        )
    }

    object TextField {
        @Composable fun ExpressionAnalysisColors(): TextFieldColors = TextFieldColors(
            indicatorColor = Colors.Orange40,
            containerColor = Colors.White,
            textColor = Colors.Brown80,
            placeholderColor = Colors.Brown100Alpha64,
            cursorColor = Colors.Orange40,
        )

        @Composable fun MainTextFieldColors(): TextFieldColors = TextFieldColors(
            containerColor = Colors.White,
            placeholderColor = Colors.Brown100Alpha64,
            leadingIconColor = Colors.Brown80,
            textColor = Colors.Brown100Alpha64,
            cursorColor = Colors.Brown80,
            focusedBorderColor = Colors.Green50,
            errorBorderColor = Colors.Orange40,
            unfocusedBorderColor = Colors.Transparent
        )

        @Composable
        fun TextFieldColors(
            indicatorColor: Color = Color.Unspecified,
            textColor: Color = Color.Unspecified,
            containerColor: Color = Color.Unspecified,
            errorColor: Color = Color.Unspecified,
            cursorColor: Color = Color.Unspecified,
            leadingIconColor: Color = Color.Unspecified,
            trailingIconColor: Color = Color.Unspecified,
            labelColor: Color = Color.Unspecified,
            placeholderColor: Color,
            supportingTextColor: Color = Color.Unspecified,
            prefixColor: Color = Color.Unspecified,
            suffixColor: Color = Color.Unspecified,
        ): TextFieldColors =
            TextFieldColors(
                focusedIndicatorColor = indicatorColor,
                unfocusedTextColor = textColor,
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedTextColor = textColor,
                disabledTextColor = textColor,
                errorTextColor = errorColor,
                disabledContainerColor = containerColor,
                errorContainerColor = errorColor,
                cursorColor = cursorColor,
                errorCursorColor = errorColor,
                textSelectionColors = TextSelectionColors(
                    handleColor = indicatorColor,
                    backgroundColor = containerColor
                ),
                unfocusedIndicatorColor = indicatorColor,
                disabledIndicatorColor = indicatorColor,
                errorIndicatorColor = errorColor,
                focusedLeadingIconColor = leadingIconColor,
                unfocusedLeadingIconColor = leadingIconColor,
                disabledLeadingIconColor = leadingIconColor,
                errorLeadingIconColor = errorColor,
                focusedTrailingIconColor = trailingIconColor,
                unfocusedTrailingIconColor = trailingIconColor,
                disabledTrailingIconColor = trailingIconColor,
                errorTrailingIconColor = errorColor,
                focusedLabelColor = labelColor,
                unfocusedLabelColor = labelColor,
                disabledLabelColor = labelColor,
                errorLabelColor = errorColor,
                focusedPlaceholderColor = placeholderColor,
                unfocusedPlaceholderColor = placeholderColor,
                disabledPlaceholderColor = placeholderColor,
                errorPlaceholderColor = errorColor,
                focusedSupportingTextColor = supportingTextColor,
                unfocusedSupportingTextColor = supportingTextColor,
                disabledSupportingTextColor = supportingTextColor,
                errorSupportingTextColor = errorColor,
                focusedPrefixColor = prefixColor,
                unfocusedPrefixColor = prefixColor,
                disabledPrefixColor = prefixColor,
                errorPrefixColor = errorColor,
                focusedSuffixColor = suffixColor,
                unfocusedSuffixColor = suffixColor,
                disabledSuffixColor = suffixColor,
                errorSuffixColor = errorColor,
            )

        @Composable
        fun TextFieldColors(
            textColor: Color = Color.Unspecified,
            containerColor: Color = Color.Unspecified,
            errorColor: Color = Color.Unspecified,
            cursorColor: Color = Color.Unspecified,
            leadingIconColor: Color = Color.Unspecified,
            trailingIconColor: Color = Color.Unspecified,
            labelColor: Color = Color.Unspecified,
            placeholderColor: Color,
            supportingTextColor: Color = Color.Unspecified,
            prefixColor: Color = Color.Unspecified,
            focusedBorderColor: Color = Color.Unspecified,
            unfocusedBorderColor: Color = Color.Unspecified,
            errorBorderColor: Color = Color.Unspecified,
        ): TextFieldColors =
            OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = textColor,
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedTextColor = textColor,
                disabledTextColor = textColor,
                errorTextColor = errorColor,
                disabledContainerColor = containerColor,
                errorContainerColor = errorColor,
                cursorColor = cursorColor,
                errorCursorColor = errorColor,
                focusedLeadingIconColor = leadingIconColor,
                unfocusedLeadingIconColor = leadingIconColor,
                disabledLeadingIconColor = leadingIconColor,
                errorLeadingIconColor = errorColor,
                focusedTrailingIconColor = trailingIconColor,
                unfocusedTrailingIconColor = trailingIconColor,
                disabledTrailingIconColor = trailingIconColor,
                errorTrailingIconColor = errorColor,
                focusedLabelColor = labelColor,
                unfocusedLabelColor = labelColor,
                disabledLabelColor = labelColor,
                errorLabelColor = errorColor,
                focusedPlaceholderColor = placeholderColor,
                unfocusedPlaceholderColor = placeholderColor,
                disabledPlaceholderColor = placeholderColor,
                errorPlaceholderColor = errorColor,
                focusedSupportingTextColor = supportingTextColor,
                unfocusedSupportingTextColor = supportingTextColor,
                disabledSupportingTextColor = supportingTextColor,
                errorSupportingTextColor = errorColor,
                focusedPrefixColor = prefixColor,
                unfocusedPrefixColor = prefixColor,
                disabledPrefixColor = prefixColor,
                errorPrefixColor = errorColor,
                disabledBorderColor = unfocusedBorderColor,
                errorBorderColor = errorBorderColor,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor
            )

        @Composable
        fun TextFieldTitleTransparentColors() =
            TextFieldColors(
                focusedTextColor = Colors.Brown80,
                unfocusedTextColor = Colors.Brown80,
                disabledTextColor = Colors.Brown80,
                errorTextColor = Colors.Brown80,
                focusedContainerColor = Colors.Transparent,
                unfocusedContainerColor = Colors.Transparent,
                disabledContainerColor = Colors.Transparent,
                errorContainerColor = Colors.Transparent,
                cursorColor = Colors.Brown80,
                errorCursorColor = Colors.Brown80,
                textSelectionColors = TextSelectionColors(
                    handleColor = Colors.Brown60,
                    backgroundColor = Colors.Green40
                ),
                focusedIndicatorColor = Colors.Transparent,
                unfocusedIndicatorColor = Colors.Transparent,
                disabledIndicatorColor = Colors.Transparent,
                errorIndicatorColor = Colors.Transparent,
                focusedLeadingIconColor = Color.Unspecified,
                unfocusedLeadingIconColor = Color.Unspecified,
                disabledLeadingIconColor = Color.Unspecified,
                errorLeadingIconColor = Color.Unspecified,
                focusedTrailingIconColor = Color.Unspecified,
                unfocusedTrailingIconColor = Color.Unspecified,
                disabledTrailingIconColor = Color.Unspecified,
                errorTrailingIconColor = Color.Unspecified,
                focusedLabelColor = Color.Unspecified,
                unfocusedLabelColor = Color.Unspecified,
                disabledLabelColor = Color.Unspecified,
                errorLabelColor = Color.Unspecified,
                focusedPlaceholderColor = Colors.Brown100Alpha64,
                unfocusedPlaceholderColor = Colors.Brown100Alpha64,
                disabledPlaceholderColor = Colors.Brown100Alpha64,
                errorPlaceholderColor = Colors.Brown100Alpha64,
                focusedSupportingTextColor = Colors.Brown80,
                unfocusedSupportingTextColor = Colors.Brown80,
                disabledSupportingTextColor = Colors.Brown80,
                errorSupportingTextColor = Colors.Brown80,
                focusedPrefixColor = Color.Unspecified,
                unfocusedPrefixColor = Color.Unspecified,
                disabledPrefixColor = Color.Unspecified,
                errorPrefixColor = Color.Unspecified,
                focusedSuffixColor = Color.Unspecified,
                unfocusedSuffixColor = Color.Unspecified,
                disabledSuffixColor = Color.Unspecified,
                errorSuffixColor = Color.Unspecified
            )

        @Composable
        fun TextFieldDescriptionTransparentColors() =
            TextFieldColors(
                focusedTextColor = Colors.Brown100Alpha64,
                unfocusedTextColor = Colors.Brown100Alpha64,
                disabledTextColor = Colors.Brown100Alpha64,
                errorTextColor = Colors.Brown100Alpha64,
                focusedContainerColor = Colors.Transparent,
                unfocusedContainerColor = Colors.Transparent,
                disabledContainerColor = Colors.Transparent,
                errorContainerColor = Colors.Transparent,
                cursorColor = Colors.Brown80,
                errorCursorColor = Colors.Brown80,
                textSelectionColors = TextSelectionColors(
                    handleColor = Colors.Brown60,
                    backgroundColor = Colors.Green40
                ),
                focusedIndicatorColor = Colors.Transparent,
                unfocusedIndicatorColor = Colors.Transparent,
                disabledIndicatorColor = Colors.Transparent,
                errorIndicatorColor = Colors.Transparent,
                focusedLeadingIconColor = Color.Unspecified,
                unfocusedLeadingIconColor = Color.Unspecified,
                disabledLeadingIconColor = Color.Unspecified,
                errorLeadingIconColor = Color.Unspecified,
                focusedTrailingIconColor = Color.Unspecified,
                unfocusedTrailingIconColor = Color.Unspecified,
                disabledTrailingIconColor = Color.Unspecified,
                errorTrailingIconColor = Color.Unspecified,
                focusedLabelColor = Color.Unspecified,
                unfocusedLabelColor = Color.Unspecified,
                disabledLabelColor = Color.Unspecified,
                errorLabelColor = Color.Unspecified,
                focusedPlaceholderColor = Colors.Brown100Alpha64,
                unfocusedPlaceholderColor = Colors.Brown100Alpha64,
                disabledPlaceholderColor = Colors.Brown100Alpha64,
                errorPlaceholderColor = Colors.Brown100Alpha64,
                focusedSupportingTextColor = Colors.Brown80,
                unfocusedSupportingTextColor = Colors.Brown80,
                disabledSupportingTextColor = Colors.Brown80,
                errorSupportingTextColor = Colors.Brown80,
                focusedPrefixColor = Color.Unspecified,
                unfocusedPrefixColor = Color.Unspecified,
                disabledPrefixColor = Color.Unspecified,
                errorPrefixColor = Color.Unspecified,
                focusedSuffixColor = Color.Unspecified,
                unfocusedSuffixColor = Color.Unspecified,
                disabledSuffixColor = Color.Unspecified,
                errorSuffixColor = Color.Unspecified
            )
    }

}