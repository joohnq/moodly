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
import com.joohnq.moodapp.view.loading.CompilingDataScreen
import com.joohnq.moodapp.view.onb.OnbScreen
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.expression_analysis_desc
import moodapp.composeapp.generated.resources.expression_analysis_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

class ExpressionAnalysisScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val moodsViewModel: MoodsViewModel = koinInject()
        val userPreferencesViewModel: UserPreferenceViewModel = koinInject()
        var desc by remember { mutableStateOf("") }

        OnboardingBaseComponent(
            page = 7,
            title = Res.string.expression_analysis_title,
            onBack = navigator::pop,
            onContinue = {
                moodsViewModel.setCurrentMoodDescription(desc)
                moodsViewModel.insertCurrentMood()
                userPreferencesViewModel.setSkipOnboardingScreen()
                navigator.push(OnbScreen())
            },
        ) {
            Text(
                stringResource(Res.string.expression_analysis_desc),
                style = TextStyles.ExpressionAnalysisDesc()
            )
            ExpressionAnalysisTextField(desc) { desc = it }
        }
    }
}