package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.MedicationsSupplements
import com.joohnq.moodapp.ui.components.IconAndTextRadioButtonVertical
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingBaseComponent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements.event.OnboardingMedicationsSupplementsEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements.state.OnboardingMedicationSupplementsState
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.medications_supplements_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingMedicationsSupplementsUI(
    state: OnboardingMedicationSupplementsState,
) {
    val (selectedOption, onEvent, onAction) = state
    val options: List<MedicationsSupplements> = remember { MedicationsSupplements.getAll() }

    OnboardingBaseComponent(
        page = 5,
        title = Res.string.medications_supplements_title,
        isContinueButtonVisible = selectedOption != null,
        onGoBack = { onEvent(OnboardingMedicationsSupplementsEvent.OnGoBack) },
        onContinue = { onEvent(OnboardingMedicationsSupplementsEvent.OnNavigateToOnboardingStressLevelScreen) },
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().aspectRatio(1f),
            userScrollEnabled = false,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(options) { option: MedicationsSupplements ->
                IconAndTextRadioButtonVertical(
                    modifier = Modifier.fillMaxSize().aspectRatio(1f),
                    paddingValues = PaddingValues(all = 16.dp),
                    text = stringResource(option.text),
                    icon = option.icon.copy(modifier = Modifier.size(Dimens.Icon)),
                    selected = selectedOption == option,
                    colors = ComponentColors.RadioButton.TextRadioButtonColors(),
                    shape = Dimens.Shape.Medium,
                    textStyle = TextStyles.TextMdBold(),
                    onClick = { onAction(OnboardingIntent.UpdateUserMedicationsSupplements(option)) }
                )
            }
        }
    }
}