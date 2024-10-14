package com.joohnq.moodapp.view.onboarding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.view.components.ExpressionAnalysisTextField
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
            onBack = { navigator.pop() },
            onContinue = { navigator.push(ExpressionAnalysisScreen()) },
        ) {
            Text(
                stringResource(Res.string.expression_analysis_desc),
                style = TextStyles.ExpressionAnalysisDesc()
            )
            ExpressionAnalysisTextField(text, { text = it })
        }
    }
}