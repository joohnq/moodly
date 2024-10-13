package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.components.TextStyles
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.expression_analysis_desc
import moodapp.composeapp.generated.resources.expression_analysis_title
import org.jetbrains.compose.resources.stringResource

class ExpressionAnalysisScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var text by remember { mutableStateOf("") }

        OnboardingBaseComponent(
            page = 7,
            title = Res.string.expression_analysis_title,
            onContinue = { navigator.push(ExpressionAnalysisScreen()) },
        ) {
            Text(
                stringResource(Res.string.expression_analysis_desc),
                style = TextStyles.ExpressionAnalysisDesc()
            )

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = null,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .heightIn(min = 250.dp).border(
                        color = Colors.Brown80Alpha25,
                        width = 4.dp,
                        shape = RoundedCornerShape(20.dp)
                    ),
                colors = TextFieldColors(
                    focusedIndicatorColor = Colors.Orange40,
                    unfocusedTextColor = Colors.Orange40,
                    focusedContainerColor = Colors.White,
                    unfocusedContainerColor = Colors.White,
                    focusedTextColor = Colors.Brown80,
                    disabledTextColor = Colors.Brown80,
                    errorTextColor = Color.Red,
                    disabledContainerColor = Colors.White,
                    errorContainerColor = Colors.White,
                    cursorColor = Colors.Orange40,
                    errorCursorColor = Color.Red,
                    textSelectionColors = TextSelectionColors(
                        handleColor = Colors.Brown80,
                        backgroundColor = Colors.Orange20
                    ),
                    unfocusedIndicatorColor = Colors.Orange40,
                    disabledIndicatorColor = Colors.Orange40,
                    errorIndicatorColor = Color.Red,
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
                    focusedPlaceholderColor = Colors.Alpha100,
                    unfocusedPlaceholderColor = Colors.Alpha100,
                    disabledPlaceholderColor = Colors.Alpha100,
                    errorPlaceholderColor = Colors.Alpha100,
                    focusedSupportingTextColor = Color.Unspecified,
                    unfocusedSupportingTextColor = Color.Unspecified,
                    disabledSupportingTextColor = Color.Unspecified,
                    errorSupportingTextColor = Color.Unspecified,
                    focusedPrefixColor = Color.Unspecified,
                    unfocusedPrefixColor = Color.Unspecified,
                    disabledPrefixColor = Color.Unspecified,
                    errorPrefixColor = Color.Unspecified,
                    focusedSuffixColor = Color.Unspecified,
                    unfocusedSuffixColor = Color.Unspecified,
                    disabledSuffixColor = Color.Unspecified,
                    errorSuffixColor = Color.Unspecified,
                ),
                maxLines = 5,
                textStyle = TextStyles.ExpressionAnalysisTextField()
            )
        }
    }
}