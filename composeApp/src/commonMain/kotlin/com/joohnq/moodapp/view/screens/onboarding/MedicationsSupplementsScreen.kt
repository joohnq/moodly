package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.MedicationsSupplements
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.MedicationsSupplementsRadioButton
import com.joohnq.moodapp.view.routes.onNavigateToStressRate
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.medications_supplements_title

@Composable
fun MedicationsSupplementsScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    isContinueButtonVisible: Boolean,
    options: List<MedicationsSupplements>,
    selectedOption: MedicationsSupplements?,
    setSelectedOption: (MedicationsSupplements) -> Unit = {},
    onGoBack: () -> Unit = {},
    onAction: () -> Unit = {},
) {
    OnboardingBaseComponent(
        page = 5,
        snackBarState = snackBarState,
        title = Res.string.medications_supplements_title,
        isContinueButtonVisible = isContinueButtonVisible,
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
                MedicationsSupplementsRadioButton(
                    option = option,
                    selected = selectedOption == option,
                    onClick = { setSelectedOption(option) }
                )
            }
        }
    }
}

@Composable
fun MedicationsSupplementsScreen(
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    navigation: NavController,
) {
    var isContinueButtonVisible by remember { mutableStateOf(false) }
    var selectedOption by rememberSaveable(stateSaver = MedicationsSupplements.getSaver()) {
        mutableStateOf(null)
    }
    val options: List<MedicationsSupplements> = remember { MedicationsSupplements.getAll() }
    val snackBarState = remember { SnackbarHostState() }

    LaunchedEffect(selectedOption) {
        isContinueButtonVisible = selectedOption != null
    }

    MedicationsSupplementsScreenUI(
        options = options,
        selectedOption = selectedOption,
        setSelectedOption = { selectedOption = it },
        snackBarState = snackBarState,
        isContinueButtonVisible = isContinueButtonVisible,
        onGoBack = navigation::popBackStack,
        onAction = {
            onboardingViewModel.updateUserMedicationsSupplements(selectedOption!!)
            navigation.onNavigateToStressRate()
        }
    )
}

@Preview
@Composable
fun MedicationsSupplementsScreenPreview() {
    MedicationsSupplementsScreenUI(
        options = MedicationsSupplements.getAll(),
        selectedOption = null,
        setSelectedOption = {},
        snackBarState = remember { SnackbarHostState() },
        isContinueButtonVisible = false,
        onGoBack = {},
    )
}


