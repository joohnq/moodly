package com.joohnq.moodapp.view.onboarding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.joohnq.moodapp.view.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.expression_analysis_desc
import moodapp.composeapp.generated.resources.expression_analysis_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object ExpressionAnalysisScreenObject

@Composable
fun ExpressionAnalysisScreen(
    onGoBack: () -> Unit,
    onNavigateToGetUserName: () -> Unit
) {
    val moodsViewModel: MoodsViewModel = koinViewModel()
    val userPreferencesViewModel: UserPreferenceViewModel = koinViewModel()
    var desc by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val ioDispatcher: CoroutineDispatcher = koinInject()

    OnboardingBaseComponent(
        page = 7,
        title = Res.string.expression_analysis_title,
        onBack = onGoBack,
        isContinueButtonVisible = desc.isNotEmpty(),
        onContinue = { onSomethingWentWrong ->
            moodsViewModel.setCurrentMoodDescription(desc)
            scope.launch(ioDispatcher) {
                val res = moodsViewModel.insertCurrentMood()
                println("res: $res")
                if (!res) onSomethingWentWrong()
                moodsViewModel.resetCurrentMood()
                val res2 = userPreferencesViewModel.setSkipOnboardingScreen()
                println("res2: $res2")
                if (!res2) onSomethingWentWrong()
                onNavigateToGetUserName()
            }
        },
    ) {
        Text(
            stringResource(Res.string.expression_analysis_desc),
            style = TextStyles.ExpressionAnalysisDesc()
        )
        ExpressionAnalysisTextField(desc) { desc = it }
    }
}