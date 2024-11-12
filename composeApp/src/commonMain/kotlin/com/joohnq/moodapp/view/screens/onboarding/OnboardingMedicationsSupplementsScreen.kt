package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.MedicationsSupplements
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.IconAndTextRadioButtonVertical
import com.joohnq.moodapp.view.routes.onNavigateToOnboardingStressLevel
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.medications_supplements_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingMedicationsSupplementsScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    selectedOption: MedicationsSupplements?,
    setSelectedOption: (MedicationsSupplements) -> Unit = {},
    onGoBack: () -> Unit = {},
    onAction: () -> Unit = {},
) {
    val options: List<MedicationsSupplements> = remember { MedicationsSupplements.getAll() }

    OnboardingBaseComponent(
        page = 5,
        snackBarState = snackBarState,
        title = Res.string.medications_supplements_title,
        isContinueButtonVisible = selectedOption != null,
        onGoBack = onGoBack,
        onContinue = onAction,
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
                    onClick = { setSelectedOption(option) }
                )
            }
        }
    }
}

@Composable
fun OnboardingMedicationsSupplementsScreen(
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    navigation: NavController,
) {
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()
    val snackBarState = remember { SnackbarHostState() }

    OnboardingMedicationsSupplementsScreenUI(
        selectedOption = onboardingState.medicationsSupplements,
        setSelectedOption = onboardingViewModel::updateUserMedicationsSupplements,
        snackBarState = snackBarState,
        onGoBack = navigation::popBackStack,
        onAction = navigation::onNavigateToOnboardingStressLevel
    )
}

@Preview
@Composable
fun OnboardingMedicationsSupplementsScreenPreview() {
    OnboardingMedicationsSupplementsScreenUI(
        selectedOption = MedicationsSupplements.ImNotTakingAny,
    )
}


