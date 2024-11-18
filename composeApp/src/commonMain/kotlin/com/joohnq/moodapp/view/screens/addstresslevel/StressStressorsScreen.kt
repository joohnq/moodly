package com.joohnq.moodapp.view.screens.addstresslevel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.entities.Stressors
import com.joohnq.moodapp.entities.ValueSetValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ContinueButton
import com.joohnq.moodapp.view.components.StressStressorCircle
import com.joohnq.moodapp.view.components.TextFieldWithLabelAndDoubleBorder
import com.joohnq.moodapp.view.components.TopBar
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToHomeGraph
import com.joohnq.moodapp.view.state.UiState.Companion.fold
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.add_stress_level
import moodapp.composeapp.generated.resources.enter_your_stressor
import moodapp.composeapp.generated.resources.other
import moodapp.composeapp.generated.resources.select_stressors
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressStressorsScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    selectedStressors: List<Stressors>,
    otherValueError: String,
    otherValue: ValueSetValue<String>,
    onAction: (StressStressorsAction) -> Unit = {},
) {
    val stressors = remember { Stressors.getAll() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarState) },
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .paddingHorizontalSmall()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                text = Res.string.add_stress_level,
                onGoBack = { onAction(StressStressorsAction.OnGoBack) }
            )
            VerticalSpacer(60.dp)
            Text(
                text = stringResource(Res.string.select_stressors),
                style = TextStyles.HeadingSmExtraBold(),
                color = Colors.Brown80,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(24.dp)
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(minSize = 100.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                content = {
                    items(stressors) { stressor ->
                        StressStressorCircle(
                            stressStressor = stressor,
                            selected = selectedStressors.contains(stressor),
                            onClick = { onAction(StressStressorsAction.OnClick(stressor)) }
                        )
                    }
                },
            )
            if (selectedStressors.any { it::class == Stressors.Other::class })
                TextFieldWithLabelAndDoubleBorder(
                    label = Res.string.other,
                    placeholder = Res.string.enter_your_stressor,
                    text = otherValue.value,
                    errorText = otherValueError,
                    focusedBorderColor = Colors.Green50Alpha25,
                    colors = ComponentColors.TextField.MainTextFieldColors(),
                    onValueChange = otherValue.setValue,
                )
            VerticalSpacer(24.dp)
            if (selectedStressors.isNotEmpty())
                ContinueButton(
                    modifier = Modifier.fillMaxWidth()
                        .testTag(TestConstants.CONTINUE_BUTTON),
                    onClick = { onAction(StressStressorsAction.OnContinue) }
                )
        }
    }
}

@Composable
fun StressStressorsScreen(
    navigation: NavController,
    stressLevelViewModel: StressLevelViewModel = sharedViewModel()
) {
    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()

    LaunchedEffect(stressLevelState.adding.stressors) {
        if (stressLevelState.adding.stressors.any { it::class == Stressors.Other::class }) {
            stressLevelViewModel.updateOtherValue("")
        }
    }

    LaunchedEffect(stressLevelState.adding.status) {
        stressLevelState.adding.status.fold(
            onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
            onSuccess = {
                navigation.onNavigateToHomeGraph()
                stressLevelViewModel.resetAddingStressLevel()
            },
        )
    }

    StressStressorsScreenUI(
        snackBarState = snackBarState,
        otherValueError = stressLevelState.adding.otherValueError,
        selectedStressors = stressLevelState.adding.stressors,
        otherValue = ValueSetValue(
            stressLevelState.adding.otherValue,
            stressLevelViewModel::updateOtherValue
        ),
        onAction = { action ->
            when (action) {
                is StressStressorsAction.OnGoBack -> navigation.popBackStack()
                is StressStressorsAction.OnClick -> stressLevelViewModel.updateAddingStressStressors(
                    action.stressor
                )

                is StressStressorsAction.OnContinue -> {
                    if (stressLevelState.adding.stressors.any { it::class == Stressors.Other::class } && stressLevelState.adding.otherValue.isEmpty()) {
                        stressLevelViewModel.updateOtherValueError("Please type your other stressor")
                        return@StressStressorsScreenUI
                    }

                    stressLevelViewModel.updateAddingStressorOtherValue()
                    stressLevelViewModel.addStressLevelRecord()
                }
            }
        }
    )
}

@Preview
@Composable
fun StressStressorsScreenPreview() {
    StressStressorsScreenUI(
        selectedStressors = listOf(Stressors.getAll().first(), Stressors.getAll().last()),
        otherValue = ValueSetValue(""),
        otherValueError = ""
    )
}