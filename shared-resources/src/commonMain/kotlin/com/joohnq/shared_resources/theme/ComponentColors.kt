package com.joohnq.shared_resources.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.SliderColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.joohnq.ui.entity.TextRadioButtonColors

object ComponentColors {
    object RadioButton {
        fun textRadioButtonColors(): TextRadioButtonColors = TextRadioButtonColors(
            selectedBackgroundColor = Colors.Green50,
            selectedContentColor = Colors.White,
            unSelectedContentColor = Colors.Brown80,
            unSelectedBackgroundColor = Colors.White,
            selectedBorderColor = Colors.Green50Alpha25,
        )

        fun iconAndTextRadioButtonHorizontalColors(colors: TextRadioButtonColors): RadioButtonColors =
            RadioButtonColors(
                selectedColor = colors.selectedContentColor,
                unselectedColor = colors.unSelectedContentColor,
                disabledSelectedColor = colors.selectedContentColor,
                disabledUnselectedColor = colors.unSelectedContentColor,
            )

        fun stressLevelRadioButtonColors(): TextRadioButtonColors =
            TextRadioButtonColors(
                selectedBackgroundColor = Colors.Orange40,
                selectedContentColor = Colors.White,
                unSelectedContentColor = Colors.Brown80,
                unSelectedBackgroundColor = Colors.White,
                selectedBorderColor = Colors.Orange40Alpha25,
            )
    }
    object Slider {
        fun sleepQualitySliderColors(): SliderColors = SliderColors(
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
        fun mainCardColors(): CardColors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Brown80
        )
    }

    object IconButton {
        fun mainButtonColors(): IconButtonColors = IconButtonColors(
            containerColor = Colors.Brown80,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Brown80,
            disabledContentColor = Colors.White
        )

        fun previousNextButton(color: Color): IconButtonColors = IconButtonColors(
            containerColor = Colors.Transparent,
            disabledContainerColor = Colors.Transparent,
            contentColor = color,
            disabledContentColor = Color.Transparent,
        )

        fun transparentButton(color: Color): IconButtonColors = IconButtonColors(
            containerColor = Colors.Transparent,
            disabledContainerColor = Colors.Transparent,
            contentColor = color,
            disabledContentColor = color,
        )
    }

    object Button {
        fun bottomNavigationActionButtonColors(): ButtonColors = ButtonColors(
            containerColor = Colors.Green60,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Green60,
            disabledContentColor = Colors.White
        )

        fun mainButtonColors(): ButtonColors = ButtonColors(
            containerColor = Colors.Brown80,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Gray60,
            disabledContentColor = Colors.Gray20
        )

        fun mainButtonColorsInverted(): ButtonColors = ButtonColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Brown80
        )

        fun deleteButtonColors(): ButtonColors = ButtonColors(
            containerColor = Colors.Orange50,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Orange50,
            disabledContentColor = Colors.White
        )

        fun textRadioButtonColors(
            selected: Boolean,
            colors: TextRadioButtonColors,
        ): ButtonColors = ButtonColors(
            containerColor = if (selected) colors.selectedBackgroundColor else colors.unSelectedBackgroundColor,
            contentColor = if (selected) colors.selectedContentColor else colors.unSelectedContentColor,
            disabledContainerColor = if (selected) colors.selectedBackgroundColor else colors.unSelectedBackgroundColor,
            disabledContentColor = if (selected) colors.selectedContentColor else colors.unSelectedContentColor
        )
    }

    object TextField {
        @Composable
        fun expressionAnalysisColors(): TextFieldColors = textFieldColors(
            indicatorColor = Colors.Transparent,
            containerColor = Colors.White,
            textColor = Colors.Gray60,
            placeholderColor = Colors.Gray40,
            cursorColor = Colors.Green40,
        )

        @Composable
        fun mainTextFieldColors(): TextFieldColors = textFieldColors(
            containerColor = Colors.White,
            placeholderColor = Colors.Brown100Alpha64,
            leadingIconColor = Colors.Brown80,
            textColor = Colors.Brown100Alpha64,
            cursorColor = Colors.Brown80,
            focusedBorderColor = Colors.Green50,
            errorBorderColor = Colors.Orange40,
            unfocusedBorderColor = Colors.Transparent
        )

        fun textFieldColors(
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
        fun textFieldColors(
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

        fun textFieldTitleTransparentColors() =
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

        fun textFieldDescriptionTransparentColors() =
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