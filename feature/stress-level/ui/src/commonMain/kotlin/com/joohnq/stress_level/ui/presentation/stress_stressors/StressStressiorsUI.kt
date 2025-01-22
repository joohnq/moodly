package com.joohnq.stress_level.ui.presentation.stress_stressors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_stress_level
import com.joohnq.shared_resources.components.ContinueButton
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.components.TextBubble
import com.joohnq.shared_resources.components.TextFieldWithLabelAndDoubleBorder
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.enter_your_stressor
import com.joohnq.shared_resources.other
import com.joohnq.shared_resources.select_stressors
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.ui.mapper.getAllStressorResource
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelIntent
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddingStressLevelState
import com.joohnq.stress_level.ui.presentation.stress_stressors.event.StressStressorsEvent
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressStressorsUI(
    snackBarState: SnackbarHostState,
    state: AddingStressLevelState,
    onAddAction: (AddStressLevelIntent) -> Unit,
    onEvent: (StressStressorsEvent) -> Unit,
) {
    val stressors = remember { getAllStressorResource() }
    val canContinue by derivedStateOf { state.stressors.isNotEmpty() }
    val containOtherInStressors by derivedStateOf { state.stressors.any { it::class == Stressor.Other::class } }

    ScaffoldSnackBar(
        snackBarHostState = snackBarState,
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .paddingHorizontalMedium()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                text = Res.string.add_stress_level,
                onGoBack = { onEvent(StressStressorsEvent.GoBack) }
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
                        TextBubble(
                            text = stressor.text,
                            onClick = {
                                onAddAction(
                                    AddStressLevelIntent.UpdateAddingStressors(stressor)
                                )
                            },
                            selected = state.stressors.contains(
                                stressor
                            ),
                        )
                    }
                },
            )
            if (containOtherInStressors)
                TextFieldWithLabelAndDoubleBorder(
                    label = Res.string.other,
                    placeholder = Res.string.enter_your_stressor,
                    text = state.otherValue,
                    errorText = state.otherValueError,
                    focusedBorderColor = Colors.Green50Alpha25,
                    colors = ComponentColors.TextField.MainTextFieldColors(),
                    onValueChange = {
                        onAddAction(
                            AddStressLevelIntent.UpdateAddingOtherValue(it)
                        )
                    },
                )
            VerticalSpacer(24.dp)
            if (canContinue)
                ContinueButton(
                    modifier = Modifier.fillMaxWidth()
                        .testTag("CONTINUE_BUTTON"),
                    onClick = { onEvent(StressStressorsEvent.Continue) }
                )
        }
    }
}