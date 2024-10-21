package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.model.entities.StressLevel
import com.joohnq.moodapp.view.components.StressRateButton
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.stress_rate_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object StressRateScreenObject

@Composable
fun StressRateScreen(
    onGoBack: () -> Unit,
    onNavigateToExpressionAnalysis: () -> Unit
) {
    val moodsViewModel: MoodsViewModel = koinViewModel()
    var selectedOption by rememberSaveable(stateSaver = StressLevel.getSaver()) {
        mutableStateOf(
            StressLevel.Three
        )
    }
    val options: List<StressLevel> = remember { StressLevel.getAll() }

    OnboardingBaseComponent(
        page = 6,
        title = Res.string.stress_rate_title,
        onBack = onGoBack,
        onContinue = {
            moodsViewModel.setCurrentMoodStressLevel(selectedOption)
            onNavigateToExpressionAnalysis()
        },
    ) {
        Text(
            stringResource(selectedOption.value),
            style = TextStyles.StressRate()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            options.forEach { option: StressLevel ->
                StressRateButton(
                    modifier = Modifier.weight(1f),
                    option = option,
                    isSelected = selectedOption == option
                ) { selectedOption = option }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(selectedOption.text),
            style = TextStyles.StressRateDesc()
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}
