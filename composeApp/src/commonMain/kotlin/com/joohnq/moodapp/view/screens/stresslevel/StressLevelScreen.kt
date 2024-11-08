package com.joohnq.moodapp.view.screens.stresslevel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.StressRateButton
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.continue_word
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelScreenUI(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    isContinueButtonVisible: Boolean,
    selectedOption: StressLevel,
    setSelectedOption: (StressLevel) -> Unit,
    onContinue: () -> Unit
) {
    val options: List<StressLevel> = remember { StressLevel.getAll() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
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
                    isSelected = selectedOption == option,
                    onClick = { setSelectedOption(option) }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(selectedOption.text),
            style = TextStyles.StressRateDesc()
        )
        Spacer(modifier = Modifier.height(24.dp))
        if (isContinueButtonVisible) {
            ButtonWithArrowRight(
                modifier = Modifier.fillMaxWidth().testTag(TestConstants.CONTINUE_BUTTON),
                text = Res.string.continue_word,
                onClick = onContinue
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StressLevelScreen(
    navigation: NavController,
    stressLevelViewModel: StressLevelViewModel = sharedViewModel(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    var selectedOption by rememberSaveable(stateSaver = StressLevel.getSaver()) {
        mutableStateOf(
            StressLevel.Three
        )
    }

    StressLevelScreenUI(
        selectedOption = selectedOption,
        snackBarHostState = snackBarHostState,
        setSelectedOption = { selectedOption = it },
        isContinueButtonVisible = true,
        onContinue = {
        }
    )
}

@Preview
@Composable
fun StressRateScreenPreview() {
    StressLevelScreenUI(
        isContinueButtonVisible = true,
        selectedOption = StressLevel.Three,
        setSelectedOption = {},
        onContinue = {}
    )
}
